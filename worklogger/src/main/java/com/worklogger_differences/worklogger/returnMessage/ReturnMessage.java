package com.worklogger_differences.worklogger.returnMessage;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ReturnMessage {
    private final String message;
    private final int statusCode;
    public ReturnMessage(@JsonProperty("message") String message,
                         @JsonProperty("status_code") int statusCode){
        this.message=message;
        this.statusCode=statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
