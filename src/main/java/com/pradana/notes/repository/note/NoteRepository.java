package com.pradana.notes.repository.note;

import org.springframework.data.repository.CrudRepository;

import com.pradana.notes.model.Note;

public interface NoteRepository extends CrudRepository<Note, String> {

}
