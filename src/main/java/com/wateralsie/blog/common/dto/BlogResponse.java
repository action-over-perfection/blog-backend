package com.wateralsie.blog.common.dto;

import lombok.Builder;

@Builder
public record BlogResponse<T>(String message, T data) {
    public static <T> BlogResponse<T> of(String message) {
        return BlogResponse.<T>builder()
                .message(message)
                .build();
    }

    public static <T> BlogResponse<T> of(String message, T data) {
        return BlogResponse.<T>builder()
                .message(message)
                .data(data)
                .build();
    }
}
