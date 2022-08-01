package com.pradana.notes.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pradana.notes.dto.model.note.NoteDto;
import com.pradana.notes.dto.response.NoteResponse;

/**
 * {@link NoteService}
 * Which contain every note behavior like CRUD things
 */
public interface NoteService {

    public ResponseEntity<NoteResponse<NoteDto>> addNote(NoteDto dto);

    public ResponseEntity<NoteResponse<NoteDto>> updateNoteById(String id, NoteDto dto);

    public ResponseEntity<NoteResponse<NoteDto>> deleteNoteById(String id);

    public ResponseEntity<NoteResponse<List<NoteDto>>> getNotes();

    public ResponseEntity<NoteResponse<NoteDto>> getNoteById(String id);
}