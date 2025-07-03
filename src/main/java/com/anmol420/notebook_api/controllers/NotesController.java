package com.anmol420.notebook_api.controllers;

import com.anmol420.notebook_api.domain.dtos.notes.NoteRequest;
import com.anmol420.notebook_api.domain.dtos.notes.NoteResponse;
import com.anmol420.notebook_api.domain.dtos.notes.NoteStatusRequest;
import com.anmol420.notebook_api.service.NotesService;
import com.anmol420.notebook_api.utils.ExtractAuthenticatedUUID;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/notes")
@RequiredArgsConstructor
public class NotesController {

    private final NotesService notesService;
    private final ExtractAuthenticatedUUID extractAuthenticatedUUID;

    @PostMapping("/create")
    public ResponseEntity<?> createNote(
            @RequestBody NoteRequest createNotesRequest,
            HttpServletRequest request
    ) {
        UUID userId = extractAuthenticatedUUID.extractUUID(request);
        if (null == userId) {
            return ResponseEntity.status(500).body("Error!");
        }
        notesService.createNote(createNotesRequest, userId);
        return ResponseEntity.status(201).body("Notes Created");
    }

    @GetMapping(path = "/getNotes")
    public ResponseEntity<?> getNotes(
            HttpServletRequest request
    ) {
        UUID userId = extractAuthenticatedUUID.extractUUID(request);
        if (null == userId) {
            return ResponseEntity.status(500).body("Error!");
        }
        List<NoteResponse> notes = notesService.getNotes(userId);
        return ResponseEntity.status(200).body(notes);
    }

    @GetMapping(path = "/getNoteById/{id}")
    public ResponseEntity<?> getNotesById(
            @PathVariable("id") UUID id,
            HttpServletRequest request
    ) {
        UUID userId = extractAuthenticatedUUID.extractUUID(request);
        if (null == userId) {
            return ResponseEntity.status(500).body("Error!");
        }
        NoteResponse note = notesService.getNote(id, userId);
        return ResponseEntity.status(200).body(note);
    }

    @PutMapping(path = "/updateNote/{id}")
    public ResponseEntity<?> updateNote(
            @PathVariable("id") UUID id,
            HttpServletRequest request,
            @RequestBody NoteRequest updateNoteRequest
    ) {
        UUID userId = extractAuthenticatedUUID.extractUUID(request);
        if (null == userId) {
            return ResponseEntity.status(500).body("Error!");
        }
        NoteResponse note = notesService.updateNote(id, userId, updateNoteRequest);
        return ResponseEntity.status(200).body(note);
    }

    @PutMapping(path = "/toggleNoteStatus/{id}")
    public ResponseEntity<?> toggleNoteStatus(
            @PathVariable("id") UUID id,
            HttpServletRequest request,
            @RequestBody NoteStatusRequest noteStatusRequest
    ) {
        UUID userId = extractAuthenticatedUUID.extractUUID(request);
        if (null == userId) {
            return ResponseEntity.status(500).body("Error!");
        }
        NoteResponse note = notesService.toggleNoteStatus(id, userId, noteStatusRequest);
        return ResponseEntity.status(200).body(note);
    }

    @DeleteMapping(path = "/deleteNote/{id}")
    public ResponseEntity<?> deleteNote(
            @PathVariable("id") UUID id,
            HttpServletRequest request
    ) {
        UUID userId = extractAuthenticatedUUID.extractUUID(request);
        if (null == userId) {
            return ResponseEntity.status(500).body("Error!");
        }
        notesService.deleteNote(id, userId);
        return ResponseEntity.ok("Deleted!");
    }
}
