package com.worklogger_differences.worklogger.returnMessage;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class ReturnMessage {
    private final String message;
    private final int statusCode;
    private List<String> difference;
    public ReturnMessage(@JsonProperty("message") String message,
                         @JsonProperty("status_code") int statusCode){
        this.message=message;
        this.statusCode=statusCode;
    }
    public ReturnMessage(@JsonProperty("message") String message,
                         @JsonProperty("status_code") int statusCode,
                         @JsonProperty("differences")List<String> dif){
        this.message=message;
        this.statusCode=statusCode;
        this.difference=dif;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
