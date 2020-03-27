package com.worklogger_differences.worklogger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CompareDifferentFilesException extends Exception{
    public static final long serialVersionUID=1L;
    public CompareDifferentFilesException(String err){
        super(err);
    }
}
