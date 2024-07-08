package br.com.app.cleanarchitecture.application.impl;

import br.com.app.cleanarchitecture.application.dto.response.UserResponse;
import br.com.app.cleanarchitecture.application.exception.EntityNotFoundException;
import br.com.app.cleanarchitecture.domain.gateway.UserGateway;
import br.com.app.cleanarchitecture.domain.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseImplTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenExistingUserId_whenGetUser_thenUserIsSuccessfullyReturned() {
        // Given
        Long userId = 1L;
        UserModel expectedUser = new UserModel(userId, "testUser", "test@example.com", "password", "Test User", "ROLE_USER", true);

        when(userGateway.findById(userId)).thenReturn(Optional.of(expectedUser));

        // When
        UserResponse actualUser = getUserUseCase.execute(userId);

        // Then
        assertNotNull(actualUser);
        assertEquals(expectedUser.getId(), actualUser.getId());
        verify(userGateway, times(1)).findById(userId);
    }

    @Test
    void givenNonExistingUserId_whenGetUser_thenThrowsNotFoundException() {
        // Given
        Long userId = 2L;
        when(userGateway.findById(userId)).thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(EntityNotFoundException.class, () -> getUserUseCase.execute(userId));

        // Then
        assertEquals("Not found with ID: " + userId, exception.getMessage());
        verify(userGateway, times(1)).findById(userId);
    }
}