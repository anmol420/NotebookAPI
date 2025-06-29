package com.anmol420.notebook_api.mapper;

import com.anmol420.notebook_api.domain.dtos.UserDTO;
import com.anmol420.notebook_api.domain.entities.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = NoteMapper.class)
public interface UserMapper {

    UserDTO toDTO(User user);

    @InheritInverseConfiguration
    @Mapping(target = "notes", ignore = true)
    User fromDTO(UserDTO userDTO);

}
