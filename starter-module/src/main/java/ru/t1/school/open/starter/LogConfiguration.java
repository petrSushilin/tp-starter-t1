package ru.t1.school.open.starter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LogProperties.class)
public class LogConfiguration {
    @Bean
    public LogService logService(LogProperties properties) {
        return new LogService(properties.getCredentials());
    }
}
