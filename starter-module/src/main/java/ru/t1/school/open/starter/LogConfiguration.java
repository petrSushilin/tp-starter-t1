package ru.t1.school.open.starter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.t1.school.open.logger.LoggingAspect;
import ru.t1.school.open.logger.LoggingAspectConfiguration;

@Configuration
@EnableConfigurationProperties(LogProperties.class)
public class LogConfiguration {
    @Bean
    public LoggingAspect loggingAspect(LoggingAspectConfiguration loggingAspectConfiguration) {
        return new LoggingAspect(loggingAspectConfiguration);
    }

    @Bean
    public LoggingAspectConfiguration loggingAspectConfiguration(LogProperties logProperties) {
        return new LoggingAspectConfiguration(logProperties.getLogger());
    }

    @Bean
    public LogProperties logProperties() {
        return new LogProperties();
    }
}
