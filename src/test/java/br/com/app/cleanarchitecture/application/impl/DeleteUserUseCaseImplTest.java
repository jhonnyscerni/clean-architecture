package br.com.app.cleanarchitecture.application.impl;

import br.com.app.cleanarchitecture.application.exception.EntityNotFoundException;
import br.com.app.cleanarchitecture.application.usecase.impl.DeleteUserUseCaseImpl;
import br.com.app.cleanarchitecture.domain.gateway.UserGateway;
import br.com.app.cleanarchitecture.domain.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseImplTest {

    @Mock
    private UserGateway userGateway;

    @Mock
    private Logger logger;

    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenExistingUserId_whenDeleteUser_thenUserIsSuccessfullyDeleted() {
        // Given
        Long userId = 1L;
        when(userGateway.findById(userId)).thenReturn(Optional.of(new UserModel())); // Assuming Object is a stand-in for the actual User class

        // When
        deleteUserUseCase.execute(userId);

        // Then
        verify(userGateway, times(1)).delete(userId);
    }

    @Test
    void givenNonExistingUserId_whenDeleteUser_thenThrowsNotFoundException() {
        // Given
        Long userId = 2L;
        when(userGateway.findById(userId)).thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(EntityNotFoundException.class, () -> deleteUserUseCase.execute(userId));

        // Then
        assertEquals("Not found with ID: " + userId, exception.getMessage());
        verify(userGateway, never()).delete(userId);
    }
}