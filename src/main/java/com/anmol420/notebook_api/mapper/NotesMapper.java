package com.anmol420.notebook_api.mapper;

import com.anmol420.notebook_api.domain.dtos.notes.NoteResponse;
import com.anmol420.notebook_api.domain.entities.Note;

public interface NotesMapper {

    NoteResponse toNotesResponse(Note note);

}
