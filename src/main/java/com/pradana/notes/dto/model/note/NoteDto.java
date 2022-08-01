package com.pradana.notes.dto.model.note;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class NoteDto {
    private String id;

    private String title;

    private String description;

    private boolean isCompleted;

    private Date createdAt;

    private Date updatedAt;
}
