package com.pradana.notes.controller.api;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pradana.notes.controller.v1.request.NoteRequest;
import com.pradana.notes.dto.mapper.NoteMapper;
import com.pradana.notes.dto.model.note.NoteDto;
import com.pradana.notes.dto.response.NoteResponse;
import com.pradana.notes.model.Note;
import com.pradana.notes.services.NoteService;

@RestController
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    @ResponseBody
    public ResponseEntity<NoteResponse<List<NoteDto>>> getNotes() {
        return noteService.getNotes();
    }

    @GetMapping("/note")
    @ResponseBody
    public ResponseEntity<NoteResponse<NoteDto>> getNoteById(@RequestParam(name = "id") String id) {
        return noteService.getNoteById(id);
    }

    // @PutMapping("/note")
    // @ResponseBody
    // public ResponseEntity<NoteResponse<NoteDto>>
    // updateNoteById(@RequestParam(name = "id") String id,
    // @Valid @RequestBody NoteRequest noteDto) {
    // return noteService.updateNoteById(id, noteDto);
    // }

    @PostMapping("/note")
    @ResponseBody
    public ResponseEntity<NoteResponse<NoteDto>> addNotes(@Valid @RequestBody NoteRequest noteDto) {
        Note model = new Note();
        model.setTitle(noteDto.getTitle());
        model.setCompleted(Boolean.valueOf(noteDto.getIsCompleted()));
        model.setDescription(noteDto.getDescription());
        return noteService.addNote(NoteMapper.toNoteDto(model));
    }

    @DeleteMapping("/note")
    @ResponseBody
    public ResponseEntity<NoteResponse<NoteDto>> deleteNoteById(@RequestParam(name = "id") String id) {
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
