package com.pradana.notes.controller.v1.api;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

/**
 * {@link NoteController} which manage API request and response
 * for Note
 * 
 */
@RestController
public class NoteController {
    @Autowired
    private NoteService noteService;

    /**
     * Used for getting all available note
     * 
     * @return
     *         {@link ResponseEntity}
     */
    @GetMapping("/notes")
    @ResponseBody
    public ResponseEntity<NoteResponse<List<NoteDto>>> getNotes() {
        return noteService.getNotes();
    }

    /**
     * Used for getting specific note by id
     * 
     * @param id
     * @return
     */
    @GetMapping("/note")
    @ResponseBody
    public ResponseEntity<NoteResponse<NoteDto>> getNoteById(@RequestParam(name = "id") String id) {
        return noteService.getNoteById(id);
    }

    /**
     * Used for updating new Note by id and with request body
     * 
     * @param id
     * @param noteDto
     * 
     * @return
     *         {@link ResponseEntity}
     */
    @PutMapping("/note/{id}")
    @ResponseBody
    public ResponseEntity<NoteResponse<NoteDto>> updateNoteById(@PathVariable(name = "id") String id,
            @Valid @RequestBody NoteRequest noteDto) {
        Note model = new Note();
        model.setTitle(noteDto.getTitle());
        model.setCompleted(Boolean.valueOf(noteDto.getIsCompleted()));
        model.setDescription(noteDto.getDescription());
        return noteService.updateNoteById(id, NoteMapper.toNoteDto(model));
    }

    /**
     * Used for insertion new note
     * 
     * @param noteDto
     * @return
     */
    @PostMapping("/note")
    @ResponseBody
    public ResponseEntity<NoteResponse<NoteDto>> addNotes(@Valid @RequestBody NoteRequest noteDto) {
        Note model = new Note();
        model.setTitle(noteDto.getTitle());
        model.setCompleted(Boolean.valueOf(noteDto.getIsCompleted()));
        model.setDescription(noteDto.getDescription());
        return noteService.addNote(NoteMapper.toNoteDto(model));
    }

    /**
     * Used for deleting note by id
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/note/{id}")
    @ResponseBody
    public ResponseEntity<NoteResponse<NoteDto>> deleteNoteById(@PathVariable(name = "id") String id) {
        return noteService.deleteNoteById(id);
    }

    /**
     * Auto configuration method for create custom error message from
     * request body
     * 
     * @param methodArgumentNotValidException
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, Object> result = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((err) -> {
            String fieldName = ((FieldError) err).getField();
            String errorMsg = err.getDefaultMessage();
            result.put(fieldName, errorMsg);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
