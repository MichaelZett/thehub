package com.buhl.hub.logging.domain;

import com.buhl.hub.logging.values.LogCall;
import com.buhl.hub.logging.values.ParamTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LogEventCollector {
    private LogEvent logEvent;
    private Map<String, Object> errors = new HashMap<>();
    private Map<String, Object> warnings = new HashMap<>();
    private List<LogCall> externals = new ArrayList<>();
    private LogCall mainCall;

    public void startLog(String classname, String method, String traceId, String env, String caller, String customerId, ParamTypes type,
                         Object request) {

        this.mainCall = new LogCall(classname, method, type, request, null, null, null);
        this.logEvent = new LogEvent(env, caller, customerId, getTraceId(traceId));
        // start Watch

    }

    public LogEvent finalizeLogEvent() {
        // put collected data into the fields
        logEvent.setWarnings((String) warnings.get("business"));
        return logEvent;
    }

    private static String getTraceId(String traceId) {
        return traceId != null && !traceId.isBlank() ? traceId : UUID.randomUUID().toString();
    }

    public void addWarning(String data) {
        warnings.put("business", data);
    }
}

