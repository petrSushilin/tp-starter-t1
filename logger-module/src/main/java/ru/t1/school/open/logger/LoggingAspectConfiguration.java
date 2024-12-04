package ru.t1.school.open.logger;

import java.util.AbstractMap;
import java.util.Map;

public class LoggingAspectConfiguration {
    private final Map<String, LoggingCustomizer> loggerCredentials;

    private static final Map.Entry<String, LoggingCustomizer> DEFAULT_LOGGER_LEVEL = new AbstractMap.SimpleEntry<>("level", LoggingLevel.SHORT);

    public LoggingAspectConfiguration(Map<String, LoggingCustomizer> loggerCredentials) {
        if (loggerCredentials == null || loggerCredentials.isEmpty()) {
            this.loggerCredentials = Map.ofEntries(
                    DEFAULT_LOGGER_LEVEL
            );
        } else {
            this.loggerCredentials = loggerCredentials;
        }
    }

    public LoggingLevel getLoggerLevel() {
        try {
            return (LoggingLevel) loggerCredentials.getOrDefault("level", DEFAULT_LOGGER_LEVEL.getValue());
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Logger level must be an instance of LoggingLevel");
        }
    }
}
