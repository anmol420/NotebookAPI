package com.anmol420.notebook_api.domain.dtos.notes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteStatusRequest {

    private boolean isPinned;
    private boolean isArchived;
    private boolean isDeleted;

}
