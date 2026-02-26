package cukibe123.lfg_platform.identity.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cukibe123.lfg_platform.identity.domain.User;
import cukibe123.lfg_platform.identity.domain.UserRepository;
import cukibe123.lfg_platform.identity.infra.security.BcryptPasswordHasher;

@ExtendWith(MockitoExtension.class)
class RegisterUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BcryptPasswordHasher hasher;

    @InjectMocks
    private RegisterUserService service;

    @Test
    void execute_hashesPasswordAndPersistsUser() {
        String email = "test@example.com";
        String rawPassword = "secret";
        String hashedPassword = "hashed-secret";

        when(hasher.hash(rawPassword)).thenReturn(hashedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(UUID.randomUUID());
            return user;
        });

        User result = service.execute(email, rawPassword);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        User saved = userCaptor.getValue();

        assertEquals(email, saved.getEmail());
        assertEquals(hashedPassword, saved.getPasswordHash());
        assertSame(saved, result);
    }

    @Test
    void execute_throwsDuplicateEmailExceptionWhenRepositorySignalsDuplicate() {
        String email = "taken@example.com";

        when(hasher.hash("pw")).thenReturn("hashed");
        when(userRepository.save(any(User.class))).thenThrow(new DuplicateEmailException(email));

        DuplicateEmailException ex = assertThrows(
            DuplicateEmailException.class,
            () -> service.execute(email, "pw")
        );

        assertEquals("Email already used " + email, ex.getMessage());
    }
}
