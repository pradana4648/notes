package com.pradana.notes.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
        note.setId(UUID.randomUUID().toString());
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

    public ResponseEntity<NoteResponse<Note>> getNoteById(String id) {
        Optional<Note> singleNoteOptional = noteRepository.findById(id);

        NoteResponse<Note> noteResponse;

        if (singleNoteOptional.isPresent()) {
            final Note result = singleNoteOptional.get();

            noteResponse = new NoteResponse<>();
            noteResponse.setStatus("ok");
            noteResponse.setMessage("success");
            noteResponse.setResult(result);

            return ResponseEntity.status(HttpStatus.OK).body(noteResponse);
        }
        noteResponse = new NoteResponse<>();
        noteResponse.setStatus("error");
        noteResponse.setMessage(String.format("note with id %s not found", id));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noteResponse);
    }

    public ResponseEntity<NoteResponse<Note>> updateNoteById(String id, NoteDto noteDto) {
        Date date = Date.from(Instant.now());
        Optional<Note> singleNoteOptional = noteRepository.findById(id);

        NoteResponse<Note> noteResponse;

        if (singleNoteOptional.isPresent()) {
            final Note note = new Note();
            note.setId(id);
            note.setTitle(noteDto.getTitle());
            note.setDescription(noteDto.getDescription());
            note.setCompleted(Boolean.valueOf(noteDto.getIsCompleted()));
            note.setCreatedAt(singleNoteOptional.get().getCreatedAt());
            note.setUpdatedAt(date);

            noteResponse = new NoteResponse<>();
            noteResponse.setStatus("ok");
            noteResponse.setMessage(String.format("success update note with id %s", id));
            noteResponse.setResult(noteRepository.save(note));

            return ResponseEntity.status(HttpStatus.OK).body(noteResponse);

        }
        noteResponse = new NoteResponse<>();
        noteResponse.setStatus("error");
        noteResponse.setMessage(String.format("note with id %s not found", id));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noteResponse);
    }

    public ResponseEntity<Map<String, Object>> deleteNoteById(String id) {
        final HashMap<String, Object> result = new HashMap<>();

        Optional<Note> singleNoteOptional = noteRepository.findById(id);

        if (singleNoteOptional.isPresent()) {
            noteRepository.deleteById(id);
            result.put("status", "ok");
            result.put("message", String.format("success deleted note with id %s", id));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }

        return null;
    }
}
