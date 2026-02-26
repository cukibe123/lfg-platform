package cukibe123.lfg_platform.identity.api.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
    @NotBlank String email,
    @NotBlank String rawPassword
) {
}
