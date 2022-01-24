package idea.verlif.exceptioncapture;

import idea.verlif.exceptioncapture.impl.DefaultBaseHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Verlif
 * @version 1.0
 * @date 2022/1/24 11:28
 */
@Configuration
public class ExceptionConfig {

    @Bean
    @ConditionalOnMissingBean(BaseExceptionHolder.class)
    public BaseExceptionHolder baseExceptionHolder() {
        return new DefaultBaseHolder();
    }
}
