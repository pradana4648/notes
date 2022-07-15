package com.pradana.notes.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.pradana.notes.dto.NoteDto;
import com.pradana.notes.pojo.Note;
import com.pradana.notes.repositories.NoteRepository;
import com.pradana.notes.templates.NoteResponse;

@Service
public class NoteService {
    private Date currentDate = Date.from(Instant.now());

    @Autowired
    private NoteRepository noteRepository;

    public ResponseEntity<NoteResponse<Note>> addNote(@RequestBody NoteDto noteDto) {

        final Note note = new Note();
        note.setTitle(noteDto.getTitle());
        note.setDescription(noteDto.getDescription());

        note.setCompleted(Boolean.valueOf(noteDto.getIsCompleted()));
        note.setCreatedAt(currentDate);
        note.setUpdatedAt(currentDate);

        final Note savedNote = noteRepository.save(note);

        final NoteResponse<Note> noteResponse = new NoteResponse<>();
        noteResponse.setStatus("ok");
        noteResponse.setMessage("success add note");
        noteResponse.setResult(savedNote);

        return ResponseEntity.status(HttpStatus.OK).body(noteResponse);
    }

    public ResponseEntity<NoteResponse<List<Note>>> getNotes() {
        final List<Note> notes = new ArrayList<>();
        noteRepository.findAll().forEach(notes::add);

        NoteResponse<List<Note>> noteResponse;

        noteResponse = new NoteResponse<>();
        noteResponse.setStatus("ok");
        noteResponse.setMessage("success");
        noteResponse.setResult(notes);

        return ResponseEntity.status(HttpStatus.OK).body(noteResponse);
    }
}
