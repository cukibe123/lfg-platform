package cukibe123.lfg_platform.identity.api.dto;

import java.util.UUID;

public record RegisterResponse(
    UUID id, 
    String email, 
    String status) {
}
