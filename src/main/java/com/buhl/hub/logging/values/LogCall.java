package com.buhl.hub.logging.values;

import com.buhl.hub.logging.values.ParamTypes;

public class LogCall {
    private String classname;
    private String method;
    private ParamTypes type;
    private Object request;

    public LogCall(String classname, String method, ParamTypes type, Object request, Object o, Object o1, Object o2) {
        this.classname = classname;
    }
}