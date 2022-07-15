package com.pradana.notes.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Note {
    @Id
    private String id;

    private String title;

    private String description;

    private boolean isCompleted;

    private Date createdAt;

    private Date updatedAt;
}
