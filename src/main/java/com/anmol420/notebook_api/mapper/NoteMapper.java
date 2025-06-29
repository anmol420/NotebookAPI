package com.anmol420.notebook_api.mapper;

import com.anmol420.notebook_api.domain.dtos.NoteDTO;
import com.anmol420.notebook_api.domain.entities.Note;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoteMapper {

    NoteDTO toDTO(Note note);

    Note fromDTO(NoteDTO noteDTO);

}
