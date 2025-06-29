package com.anmol420.notebook_api.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private List<NoteDTO> notes;

    @JsonProperty("fullName")
    private String getFullName() {
        if (null == lastName) {
            return firstName;
        }
        return firstName + " " + lastName;
    }

}
