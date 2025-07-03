package com.anmol420.notebook_api.service.impl;

import com.anmol420.notebook_api.domain.dtos.notes.NoteRequest;
import com.anmol420.notebook_api.domain.dtos.notes.NoteResponse;
import com.anmol420.notebook_api.domain.dtos.notes.NoteStatusRequest;
import com.anmol420.notebook_api.domain.entities.Note;
import com.anmol420.notebook_api.domain.entities.User;
import com.anmol420.notebook_api.mapper.NotesMapper;
import com.anmol420.notebook_api.repository.NoteRepository;
import com.anmol420.notebook_api.repository.UserRepository;
import com.anmol420.notebook_api.service.NotesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final NotesMapper notesMapper;

    @Transactional
    @Override
    public void createNote(NoteRequest request, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User Not Found!"));
        Optional<Note> noteFound = noteRepository.findByTitle(request.getTitle());
        if (noteFound.isPresent()) {
            throw new IllegalArgumentException("Title Already Exists!");
        }
        Note note = Note.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .isDeleted(false)
                .isArchived(false)
                .isPinned(false)
                .build();
        noteRepository.save(note);
    }

    @Override
    public NoteResponse getNote(UUID notesId, UUID userId) {
        Note note = noteRepository.findByUserIdAndId(userId, notesId);
        return notesMapper.toNotesResponse(note);
    }

    @Override
    public List<NoteResponse> getNotes(UUID userId) {
        List<Note> notes = noteRepository.findByUserId(userId);
        return notes.stream()
                .map(notesMapper::toNotesResponse)
                .toList();
    }

    @Transactional
    @Override
    public NoteResponse updateNote(UUID notesId, UUID userId, NoteRequest request) {
        Note note = noteRepository.findByUserIdAndId(userId, notesId);
        if (null == request.getTitle()) {
            throw new IllegalArgumentException("Title is Required!");
        }
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        noteRepository.save(note);
        return notesMapper.toNotesResponse(note);
    }

    @Transactional
    @Override
    public NoteResponse toggleNoteStatus(UUID notesId, UUID userId, NoteStatusRequest request) {
        Note note = noteRepository.findByUserIdAndId(userId, notesId);
        note.setPinned(request.isPinned());
        note.setArchived(request.isArchived());
        note.setDeleted(request.isDeleted());
        noteRepository.save(note);
        return notesMapper.toNotesResponse(note);
    }

    @Transactional
    @Override
    public void deleteNote(UUID noteId, UUID userId) {
        noteRepository.deleteByUserIdAndId(userId, noteId);
    }
}
