package com.buhl.hub.configuration;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.buhl.hub.logging.adapter.LoggerInterceptor;
import com.buhl.hub.logging.application.HubDbAppender;
import com.buhl.hub.logging.domain.LogEventRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final LogEventRepository logEventRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        final Logger logger = createSpecialDbLogger();

        final LoggerInterceptor interceptor = new LoggerInterceptor(logger);
        registry.addInterceptor(interceptor);
    }

    private Logger createSpecialDbLogger() {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        final HubDbAppender hubDbAppender = new HubDbAppender(logEventRepository);
        hubDbAppender.setContext(lc);
        hubDbAppender.start();

        Logger logger = (Logger) LoggerFactory.getLogger("com.buhl.hub.logging.adapter.LoggerInterceptor");
        logger.addAppender(hubDbAppender);
        logger.setLevel(Level.INFO);
//      set to false if root should not log
//		logger.setAdditive(false);
        return logger;
    }
}