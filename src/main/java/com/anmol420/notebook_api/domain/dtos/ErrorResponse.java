package com.anmol420.notebook_api.domain.dtos;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
