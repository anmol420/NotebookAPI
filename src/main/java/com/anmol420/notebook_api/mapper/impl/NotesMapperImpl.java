package com.anmol420.notebook_api.mapper.impl;

import com.anmol420.notebook_api.domain.dtos.notes.NoteResponse;
import com.anmol420.notebook_api.domain.entities.Note;
import com.anmol420.notebook_api.mapper.NotesMapper;
import org.springframework.stereotype.Component;

@Component
public class NotesMapperImpl implements NotesMapper {

    @Override
    public NoteResponse toNotesResponse(Note note) {
        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.isPinned(),
                note.isDeleted(),
                note.isDeleted(),
                note.getCreatedAt()
        );
    }

}
