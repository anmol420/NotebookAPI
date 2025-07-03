package com.anmol420.notebook_api.domain.dtos.notes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteResponse {

    private UUID id;
    private String title;
    private String content;
    private boolean isPinned;
    private boolean isDeleted;
    private boolean isArchived;
    private LocalDateTime isCreated;

}
