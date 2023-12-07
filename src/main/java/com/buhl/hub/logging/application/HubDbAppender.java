package com.buhl.hub.logging.application;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.buhl.hub.logging.domain.LogEvent;
import com.buhl.hub.logging.domain.LogEventRepository;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
public class HubDbAppender extends AppenderBase<ILoggingEvent> {
    private final LogEventRepository logEventRepository;

    @Override
    protected void append(ILoggingEvent event) {
        logEventRepository.save(new LogEvent(event.getLoggerName(), event.getLevel().toString(), Instant.ofEpochMilli(event.getTimeStamp()), event.getMessage()));
    }

}