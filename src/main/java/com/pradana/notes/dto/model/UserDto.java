package com.pradana.notes.dto.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserDto {
    @Id
    private String id;

    private String username;

    private String email;

    private String password;
}
