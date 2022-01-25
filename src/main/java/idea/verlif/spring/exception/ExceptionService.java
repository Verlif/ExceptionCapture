package idea.verlif.spring.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Verlif
 * @version 1.0
 * @date 2021/11/10 10:13
 */
@ControllerAdvice
public class ExceptionService {

    private Map<Class<? extends Throwable>, ExceptionHolder<?>> holderMap;

    @Autowired
    private BaseExceptionHolder baseHolder;

    @Autowired
    private ApplicationContext context;

    @PostConstruct
    public void load() {
        Map<String, ExceptionHolder> map = context.getBeansOfType(ExceptionHolder.class);
        holderMap = new HashMap<>(map.size());
        for (ExceptionHolder<?> holder : map.values()) {
            holderMap.put(holder.register(), holder);
        }
    }

    /**
     * 全局异常处理，由handler方法统一分配 <br/>
     * 后期可能会更改判定逻辑，当未找到自定处理类时，会向其父类寻找
     *
     * @param e   异常对象
     * @param <T> 异常泛型
     * @return 前端回传数据
     */
    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public <T extends Throwable> Object handler(T e) {
        Class<?> cl = e.getClass();
        ExceptionHolder<T> holder;
        // 获取可能存在异常的处理类
        do {
            holder = (ExceptionHolder<T>) holderMap.get(cl);
            cl = cl.getSuperclass();
        } while (holder == null && cl != null);
        // 未找到时采用一般处理方式
        if (holder != null) {
            return holder.handler(e);
        } else {
            return baseHolder.handler(e);
        }
    }
}