package com.pradana.notes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pradana.notes.dto.NoteDto;
import com.pradana.notes.pojo.Note;
import com.pradana.notes.services.NoteService;
import com.pradana.notes.templates.NoteResponse;

@RestController
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    @ResponseBody
    public ResponseEntity<NoteResponse<List<Note>>> getNotes() {
        return noteService.getNotes();
    }

    @GetMapping("/note")
    @ResponseBody
    public ResponseEntity<NoteResponse<Note>> getNoteById(@RequestParam(name = "id") String id) {
        return noteService.getNoteById(id);
    }

    @PutMapping("/note")
    @ResponseBody
    public ResponseEntity<NoteResponse<Note>> updateNoteById(@RequestParam(name = "id") String id,
            @Valid @RequestBody NoteDto noteDto) {
        return noteService.updateNoteById(id, noteDto);
    }

    @PostMapping("/note")
    @ResponseBody
    public ResponseEntity<NoteResponse<Note>> addNotes(@Valid @RequestBody NoteDto noteDto) {
        return noteService.addNote(noteDto);
    }

    @DeleteMapping("/note")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteNoteById(@RequestParam(name = "id") String id) {
        return noteService.deleteNoteById(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, Object> result = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((err) -> {
            String fieldName = ((FieldError) err).getField();
            String errorMsg = err.getDefaultMessage();
            result.put(fieldName, errorMsg);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
