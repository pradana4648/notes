package com.pradana.notes.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq")
    private Long id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;
}
