package com.pradana.notes.dto.response;

import lombok.Data;

/**
 * Mananging every note request from user and return
 * response based on result
 */
@Data
public class NoteResponse<T> {
    private final String status;
    private final String message;
    private final T result;
}
