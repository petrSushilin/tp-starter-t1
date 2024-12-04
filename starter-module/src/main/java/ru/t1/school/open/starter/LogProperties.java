package ru.t1.school.open.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import ru.t1.school.open.logger.LoggingLevel;

import java.util.Map;

@ConfigurationProperties(prefix = "t1-starter")
public class LogProperties {
    private Map<String, LoggingLevel> logger;

    public Map<String, LoggingLevel> getLogger() {
        return logger;
    }

    public void setLogger(Map<String, LoggingLevel> logger) {
        this.logger = logger;
    }
}
