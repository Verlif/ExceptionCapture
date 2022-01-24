package idea.verlif.exceptioncapture;

/**
 * 基础错误处理类
 *
 * @author Verlif
 * @version 1.0
 * @date 2022/1/24 11:22
 */
public interface BaseExceptionHolder {

    /**
     * 异常处理
     *
     * @param throwable 异常对象
     * @return 返回值
     */
    Object handler(Throwable throwable);
}
