package com.pradana.notes.dto.mapper;

import com.pradana.notes.dto.model.note.NoteDto;
import com.pradana.notes.model.Note;

public class NoteMapper {
    public static NoteDto toNoteDto(Note note) {
        NoteDto dto = new NoteDto();
        dto.setId(note.getId());
        dto.setTitle(note.getTitle());
        dto.setDescription(note.getDescription());
        dto.setCompleted(note.isCompleted());
        dto.setCreatedAt(note.getCreatedAt());
        dto.setUpdatedAt(note.getUpdatedAt());
        return dto;
    }
}
