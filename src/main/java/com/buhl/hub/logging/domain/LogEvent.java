package com.buhl.hub.logging.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Getter
public class LogEvent {
    @Id
    private Long id;
    private String logger;
    private String level;
    private Instant timeStamp;
    private String message;

    public LogEvent(String logger, String level, Instant timeStamp, String message) {
        this.logger = logger;
        this.level = level;
        this.timeStamp = timeStamp;
        this.message = message;
    }
}
