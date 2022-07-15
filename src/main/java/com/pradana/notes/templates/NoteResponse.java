package com.pradana.notes.templates;

import lombok.Data;

@Data
public class NoteResponse<T> {
    private String status;
    private String message;
    private T result;
}
