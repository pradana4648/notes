package com.pradana.notes.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.pradana.notes.dto.mapper.NoteMapper;
import com.pradana.notes.dto.model.note.NoteDto;
import com.pradana.notes.dto.response.NoteResponse;
import com.pradana.notes.model.Note;
import com.pradana.notes.repository.note.NoteRepository;

@Component
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository repository;

    @Override
    public ResponseEntity<NoteResponse<NoteDto>> addNote(NoteDto dto) {
        Note model = new Note();
        model.setId(dto.getId());
        model.setTitle(dto.getTitle());
        model.setDescription(dto.getDescription());
        model.setCompleted(dto.isCompleted());
        model.setCreatedAt(dto.getCreatedAt());
        model.setUpdatedAt(dto.getUpdatedAt());

        Note entity = repository.save(model);

        ResponseEntity<NoteResponse<NoteDto>> response = ResponseEntity.status(HttpStatus.OK)
                .body(new NoteResponse<NoteDto>(HttpStatus.OK.getReasonPhrase(), "success",
                        NoteMapper.toNoteDto(entity)));

        return response;
    }

    @Override
    public ResponseEntity<NoteResponse<NoteDto>> updateNote(NoteDto dto) {
        Note model;
        ResponseEntity<NoteResponse<NoteDto>> response;

        // Find note if existed
        Optional<Note> noteFindById = repository.findById(dto.getId());
        if (noteFindById.isPresent()) {
            model = new Note();
            model.setId(dto.getId());
            model.setTitle(dto.getTitle());
            model.setDescription(dto.getDescription());
            model.setCompleted(dto.isCompleted());
            model.setCreatedAt(dto.getCreatedAt());
            model.setUpdatedAt(dto.getUpdatedAt());

            response = ResponseEntity.status(HttpStatus.OK)
                    .body(new NoteResponse<>(HttpStatus.OK.getReasonPhrase(), "success",
                            NoteMapper.toNoteDto(noteFindById.get())));
            return response;

        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new NoteResponse<>(HttpStatus.NOT_FOUND.getReasonPhrase(),
                            String.format("note with id ${0} not found", dto.getId()), null));
            return response;
        }
    }

    @Override
    public ResponseEntity<NoteResponse<NoteDto>> deleteNoteById(String id) {
        ResponseEntity<NoteResponse<NoteDto>> response;
        Optional<Note> noteFindById = repository.findById(id);

        if (noteFindById.isPresent()) {
            Note deletedNote = noteFindById.get();
            repository.delete(noteFindById.get());
            response = ResponseEntity.status(HttpStatus.OK).body(new NoteResponse<>(HttpStatus.OK.getReasonPhrase(),
                    String.format("success delete note with id ${0}", id), NoteMapper.toNoteDto(deletedNote)));
            return response;
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new NoteResponse<>(HttpStatus.NOT_FOUND.getReasonPhrase(),
                            String.format("note with id ${0} not found", id), null));
            return response;
        }
    }

    @Override
    public ResponseEntity<NoteResponse<List<NoteDto>>> getNotes() {

        List<NoteDto> notes = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(note -> NoteMapper.toNoteDto(note)).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new NoteResponse<>(HttpStatus.OK.getReasonPhrase(), "success", notes));
    }

    @Override
    public ResponseEntity<NoteResponse<NoteDto>> getNoteById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

}
