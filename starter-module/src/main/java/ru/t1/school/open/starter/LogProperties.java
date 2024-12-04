package ru.t1.school.open.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.t1.school.open.logger.LoggingCustomizer;

import java.util.Map;

@ConfigurationProperties(prefix = "t1-starter")
public class LogProperties {
    private Map<String, LoggingCustomizer> logger;

    public Map<String, LoggingCustomizer> getLogger() {
        return logger;
    }

    public void setLogger(Map<String, LoggingCustomizer> logger) {
        this.logger = logger;
    }
}
