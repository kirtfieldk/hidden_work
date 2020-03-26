package com.worklogger_differences.worklogger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "File not found")
public class FileNotFoundInDbException extends Exception {
    public static final long serialVersionUID=1L;
    public FileNotFoundInDbException(String err){
        super(err);
    }
}