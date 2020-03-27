package com.worklogger_differences.worklogger.exception;


public class FileNotFoundInDbException extends Exception {
    public static final long serialVersionUID=1L;
    public FileNotFoundInDbException(String err){
        super(err);
    }
}