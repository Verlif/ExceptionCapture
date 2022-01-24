package idea.verlif.exceptioncapture;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用全局异常捕获处理
 *
 * @author Verlif
 * @version 1.0
 * @date 2022/1/24 14:08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Configuration
@Documented
@Import({ExceptionConfig.class, ExceptionService.class})
public @interface EnableExceptionCapture {
}
