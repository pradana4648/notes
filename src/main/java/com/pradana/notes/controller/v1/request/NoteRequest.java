package com.pradana.notes.controller.v1.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class NoteRequest {
    @NotNull(message = "Title is required")
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 4, message = "Title must be at least 4 characters long")
    private String title;

    @NotNull(message = "Description is required")
    @NotBlank(message = "Description cannot be empty")
    @Size(min = 4, message = "Description must be at least 4 characters long")
    private String description;

    @NotNull
    @Pattern(regexp = "^true$|^false$", message = "Input not allowed: Must be true or false")
    private String isCompleted;
}
