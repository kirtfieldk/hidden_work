package com.worklogger_differences.worklogger.exception;

public class MissingParamsException extends Exception{
    public static final long serialVersionUID=1L;
    public MissingParamsException(String err){
        super(err);
    }
}
