package com.pradana.notes.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pradana.notes.dto.model.note.NoteDto;
import com.pradana.notes.dto.response.NoteResponse;

// @Service
// public class NoteService {
//     private Date currentDate = Date.from(Instant.now());

//     @Autowired
//     private NoteRepository noteRepository;

//     public ResponseEntity<NoteResponse<NoteDto>> addNote(@RequestBody NoteRequest noteDto) {

//         final NoteDto note = new NoteDto();
//         note.setId(UUID.randomUUID().toString());
//         note.setTitle(noteDto.getTitle());
//         note.setDescription(noteDto.getDescription());
//         note.setCompleted(Boolean.valueOf(noteDto.getIsCompleted()));
//         note.setCreatedAt(currentDate);
//         note.setUpdatedAt(currentDate);

//         final NoteDto savedNote = noteRepository.save(note);

//         final NoteResponse<NoteDto> noteResponse = new NoteResponse<>();
//         noteResponse.setStatus("ok");
//         noteResponse.setMessage("success add note");
//         noteResponse.setResult(savedNote);

//         return ResponseEntity.status(HttpStatus.OK).body(noteResponse);
//     }

//     public ResponseEntity<NoteResponse<List<NoteDto>>> getNotes() {
//         final List<NoteDto> notes = new ArrayList<>();
//         noteRepository.findAll().forEach(notes::add);

//         NoteResponse<List<NoteDto>> noteResponse;

//         noteResponse = new NoteResponse<>();
//         noteResponse.setStatus("ok");
//         noteResponse.setMessage("success");
//         noteResponse.setResult(notes);

//         return ResponseEntity.status(HttpStatus.OK).body(noteResponse);
//     }

//     public ResponseEntity<NoteResponse<NoteDto>> getNoteById(String id) {
//         Optional<NoteDto> singleNoteOptional = noteRepository.findById(id);

//         NoteResponse<NoteDto> noteResponse;

//         if (singleNoteOptional.isPresent()) {
//             final NoteDto result = singleNoteOptional.get();

//             noteResponse = new NoteResponse<>();
//             noteResponse.setStatus("ok");
//             noteResponse.setMessage("success");
//             noteResponse.setResult(result);

//             return ResponseEntity.status(HttpStatus.OK).body(noteResponse);
//         }
//         noteResponse = new NoteResponse<>();
//         noteResponse.setStatus("error");
//         noteResponse.setMessage(String.format("note with id %s not found", id));

//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noteResponse);
//     }

//     public ResponseEntity<NoteResponse<NoteDto>> updateNoteById(String id, NoteRequest noteDto) {
//         Date date = Date.from(Instant.now());
//         Optional<NoteDto> singleNoteOptional = noteRepository.findById(id);

//         NoteResponse<NoteDto> noteResponse;

//         if (singleNoteOptional.isPresent()) {
//             final NoteDto note = new NoteDto();
//             note.setId(id);
//             note.setTitle(noteDto.getTitle());
//             note.setDescription(noteDto.getDescription());
//             note.setCompleted(Boolean.valueOf(noteDto.getIsCompleted()));
//             note.setCreatedAt(singleNoteOptional.get().getCreatedAt());
//             note.setUpdatedAt(date);

//             noteResponse = new NoteResponse<>();
//             noteResponse.setStatus("ok");
//             noteResponse.setMessage(String.format("success update note with id %s", id));
//             noteResponse.setResult(noteRepository.save(note));

//             return ResponseEntity.status(HttpStatus.OK).body(noteResponse);

//         }
//         noteResponse = new NoteResponse<>();
//         noteResponse.setStatus("error");
//         noteResponse.setMessage(String.format("note with id %s not found", id));

//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noteResponse);
//     }

//     public ResponseEntity<Map<String, Object>> deleteNoteById(String id) {
//         final HashMap<String, Object> result = new HashMap<>();

//         Optional<NoteDto> singleNoteOptional = noteRepository.findById(id);

//         if (singleNoteOptional.isPresent()) {
//             noteRepository.deleteById(id);
//             result.put("status", "ok");
//             result.put("message", String.format("success deleted note with id %s", id));
//             return ResponseEntity.status(HttpStatus.OK).body(result);
//         }

//         return null;
//     }
// }

/**
 * {@link NoteService}
 * Which contain every note behavior like CRUD things
 */
public interface NoteService {

    public ResponseEntity<NoteResponse<NoteDto>> addNote(NoteDto dto);

    public ResponseEntity<NoteResponse<NoteDto>> updateNote(NoteDto dto);

    public ResponseEntity<NoteResponse<NoteDto>> deleteNoteById(String id);

    public ResponseEntity<NoteResponse<List<NoteDto>>> getNotes();

    public ResponseEntity<NoteResponse<NoteDto>> getNoteById(String id);
}