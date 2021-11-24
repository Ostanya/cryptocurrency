package com.ostapenko.cryptocurrency.exception;

public class NoMatchesException extends RuntimeException{
    public NoMatchesException(){}

    public NoMatchesException(String e) {
        super(e);
    }
}
