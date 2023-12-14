package com.buhl.hub.logging.application;

import com.buhl.hub.logging.domain.LogEventCollector;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public final class LogEventCollectorHelper {
    private LogEventCollectorHelper() {
        // not intended
    }

    public static Optional<LogEventCollector> getLogEventCollector() {
        if (RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes servletRequestAttributes) {
            final HttpServletRequest request = servletRequestAttributes.getRequest();
            final LogEventCollector collector = (LogEventCollector) request.getAttribute(LogEventCollector.NAME);
            return Optional.ofNullable(collector);
        } else {
            return Optional.empty();
        }
    }
}
