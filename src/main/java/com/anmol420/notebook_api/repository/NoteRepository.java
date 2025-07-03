package com.anmol420.notebook_api.repository;

import com.anmol420.notebook_api.domain.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {

    Optional<Note> findByTitle(String title);
    List<Note> findByUserId(UUID userId);
    Note findByUserIdAndId(UUID userId, UUID id);
    void deleteByUserIdAndId(UUID userId, UUID id);

}
