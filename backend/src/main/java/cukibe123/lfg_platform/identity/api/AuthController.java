package cukibe123.lfg_platform.identity.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cukibe123.lfg_platform.identity.api.dto.RegisterRequest;
import cukibe123.lfg_platform.identity.api.dto.RegisterResponse;
import cukibe123.lfg_platform.identity.app.RegisterUserService;
import cukibe123.lfg_platform.identity.domain.User;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegisterUserService registerUserService;

    public AuthController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        User user = registerUserService.execute(request.email(), request.rawPassword());
        RegisterResponse body = new RegisterResponse(user.getId(), user.getEmail(), user.getStatus().name());
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
