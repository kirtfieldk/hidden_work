package com.worklogger_differences.worklogger.exception;

public class FileAlreadyInDb extends Exception {
    public static final long serialVersionUID = 1L;

    public FileAlreadyInDb(String err) {
        super(err);
    }
}

