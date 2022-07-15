package com.pradana.notes.repositories;

import org.springframework.data.repository.CrudRepository;

import com.pradana.notes.pojo.Note;

public interface NoteRepository extends CrudRepository<Note, String> {

}
