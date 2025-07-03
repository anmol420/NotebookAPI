package com.anmol420.notebook_api.service;

import com.anmol420.notebook_api.domain.dtos.notes.NoteRequest;
import com.anmol420.notebook_api.domain.dtos.notes.NoteResponse;
import com.anmol420.notebook_api.domain.dtos.notes.NoteStatusRequest;

import java.util.List;
import java.util.UUID;

public interface NotesService {

    void createNote(NoteRequest request, UUID userId);
    NoteResponse getNote(UUID notesId, UUID userId);
    List<NoteResponse> getNotes(UUID userId);
    NoteResponse updateNote(UUID notesId, UUID userId, NoteRequest request);
    NoteResponse toggleNoteStatus(UUID notesId, UUID userId, NoteStatusRequest request);
    void deleteNote(UUID noteId, UUID userId);
}
