package com.pradana.notes.dto.response;

import lombok.Data;

@Data
public class UserResponse<T> {
    private String status;
    private String message;
    private T result;
}
