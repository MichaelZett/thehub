package com.buhl.hub.logging.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Table("log")
public class LogEvent {

    @Id
    private long id;
    private LocalDateTime dateEntered;
    private LocalDateTime lastExecution;
    private long duration;
    private long durationExternal;
    private long durationDb;
    private String errors;
    @Setter
    private String warnings;
    private String tracelink;
    private String traceid;
    private String environment;
    private String caller;
    private String customerId;
    @Column("interface")
    private String interfaceUsed;
    private String method;
    private String server;
    private String request;
    private String requestType;
    private String additionalCalls;
    private int tries;

    public LogEvent(String environment, String caller, String customerId, String traceId) {
        this.dateEntered = LocalDateTime.now();
        this.environment = environment;
        this.caller = caller;
        this.customerId = customerId;
        this.traceid = traceId;
    }
}

