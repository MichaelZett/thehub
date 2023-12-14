package com.buhl.hub.logging.adapter;

import com.buhl.hub.logging.domain.LogEventCollector;
import com.buhl.hub.logging.domain.LogEventRepository;
import com.buhl.hub.logging.values.ParamTypes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.Optional;


@RequiredArgsConstructor
public class LoggerInterceptor implements HandlerInterceptor {
    private final LogEventRepository logEventRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod handlerMethod) {
            String className = handlerMethod.getBeanType().getName();
            String methodName = handlerMethod.getMethod().getName();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String caller = null;
            if (authentication != null) {
                caller = authentication.getName();
            }

            Map<String, String[]> paramMap = request.getParameterMap();
            String contactNo = null;
            if (paramMap.get("contactNo") != null) {
                contactNo = paramMap.get("contactNo")[0];
            }
            LogEventCollector collector = new LogEventCollector();
            collector.startLog(className, methodName, null, "TEST",
                    caller, contactNo, ParamTypes.FORMPARAMS, request);
            request.setAttribute(LogEventCollector.NAME, collector);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (ex != null) {
            ex.printStackTrace();
        }
        final LogEventCollector collector = (LogEventCollector) request.getAttribute(LogEventCollector.NAME);
        if (collector != null) {
            logEventRepository.save(collector.finalizeLogEvent());
        }
    }

}