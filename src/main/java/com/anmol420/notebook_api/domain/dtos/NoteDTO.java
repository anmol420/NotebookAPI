package com.anmol420.notebook_api.domain.dtos;

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
public class NoteDTO {

    private UUID id;
    private String title;
    private String content;
    private boolean isPinned;
    private boolean isArchived;
    private boolean isDeleted;
    private LocalDateTime createdAt;

}
