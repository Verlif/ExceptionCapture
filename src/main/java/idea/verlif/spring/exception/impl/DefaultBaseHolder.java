package idea.verlif.spring.exception.impl;

import idea.verlif.spring.exception.BaseExceptionHolder;

/**
 * @author Verlif
 * @version 1.0
 * @date 2022/1/24 11:24
 */
public class DefaultBaseHolder implements BaseExceptionHolder {

    @Override
    public Object handler(Throwable throwable) {
        throwable.printStackTrace();
        return throwable.getMessage();
    }

}
