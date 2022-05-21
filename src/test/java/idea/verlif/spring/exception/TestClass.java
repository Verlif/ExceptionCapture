package idea.verlif.spring.exception;

import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Verlif
 */
public class TestClass {

    @Test
    public void test() {
        ExceptionHolder<?> holder = new IOExceptionHolder();
        System.out.println(holder.register());
    }

    public static class IOExceptionHolder implements ExceptionHolder<IOException> {

        @Override
        public Object handler(IOException e) {
            return "";
        }
    }
}
