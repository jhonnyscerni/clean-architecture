package br.com.app.cleanarchitecture.application.impl;

import br.com.app.cleanarchitecture.application.dto.request.UserRequest;
import br.com.app.cleanarchitecture.application.dto.response.UserResponse;
import br.com.app.cleanarchitecture.application.exception.EntityNotFoundException;
import br.com.app.cleanarchitecture.application.mapper.UserModelMapper;
import br.com.app.cleanarchitecture.application.usecase.impl.UpdateUserUseCaseImpl;
import br.com.app.cleanarchitecture.domain.gateway.UserGateway;
import br.com.app.cleanarchitecture.domain.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateUserUseCaseImplTest {

    @Mock
    private UserModelMapper userModelMapper;

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenExistingUserId_whenUpdateUser_thenUserIsSuccessfullyUpdated() {
        // Given
        Long userId = 1L;
        UserRequest updateUserRequest = new UserRequest(userId, "updatedUser", "updated@example.com", "updatedPassword", "Updated User", "ROLE_ADMIN", false);
        UserModel existingUser = new UserModel(userId, "testUser", "test@example.com", "password", "Test User", "ROLE_USER", true);
        UserModelMapper.synchronizeUserModelWithRequest(existingUser, updateUserRequest);

        when(userGateway.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userGateway.update(any(UserModel.class))).thenReturn(existingUser);

        // When
        UserResponse result = updateUserUseCase.execute(userId, updateUserRequest);

        // Then
        assertNotNull(result);
        assertEquals(existingUser.getId(), result.getId());
        assertEquals(existingUser.getUsername(), result.getUsername());
        assertEquals(existingUser.getEmail(), result.getEmail());
        assertEquals(existingUser.getName(), result.getName());
        assertEquals(existingUser.getRole(), result.getRole());
        assertEquals(existingUser.isEnabled(), result.isEnabled());

        verify(userGateway, times(1)).findById(userId);
        verify(userGateway, times(1)).update(any(UserModel.class));
    }

    @Test
    void givenNonExistingUserId_whenUpdateUser_thenThrowsNotFoundException() {
        // Given
        Long userId = 2L;
        UserRequest updateUserRequest = new UserRequest(userId, "updatedUser", "updated@example.com", "updatedPassword", "Updated User", "ROLE_ADMIN", true);

        when(userGateway.findById(userId)).thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(EntityNotFoundException.class, () -> updateUserUseCase.execute(userId, updateUserRequest));

        // Then
        assertEquals("Not found with ID: " + userId, exception.getMessage());
        verify(userGateway, times(1)).findById(userId);
        verify(userGateway, never()).update(any(UserModel.class));
    }

    @Test
    void givenExistingUserAndUserRequest_whenSynchronizeUserModelWithRequest_thenUserModelIsUpdatedAccordingly() {
        Long userId = 1L;
        UserRequest updateUserRequest = new UserRequest(userId, "updatedUser", "updated@example.com", "updatedPassword", "Updated User", "ROLE_ADMIN", false);
        UserModel existingUser = new UserModel(userId, "testUser", "test@example.com", "password", "Test User", "ROLE_USER", true);

        when(userGateway.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userGateway.update(any(UserModel.class))).thenReturn(existingUser);

        try (MockedStatic<UserModelMapper> mocked = Mockito.mockStatic(UserModelMapper.class)) {
            mocked.when(() -> UserModelMapper.synchronizeUserModelWithRequest(existingUser, updateUserRequest))
                    .thenAnswer(invocation -> {
                        UserModel user = invocation.getArgument(0);
                        UserRequest request = invocation.getArgument(1);
                        user.setUsername(request.getUsername());
                        user.setEmail(request.getEmail());
                        user.setPassword(request.getPassword());
                        user.setName(request.getName());
                        user.setRole(request.getRole());
                        user.setEnabled(request.isEnabled());
                        return user;
                    });

            updateUserUseCase.execute(userId, updateUserRequest);

            mocked.verify(() -> UserModelMapper.synchronizeUserModelWithRequest(existingUser, updateUserRequest));
        }
    }
}