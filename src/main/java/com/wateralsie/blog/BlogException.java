package com.wateralsie.blog;

public class BlogException extends RuntimeException {
    private final int statusCode;

    public BlogException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
