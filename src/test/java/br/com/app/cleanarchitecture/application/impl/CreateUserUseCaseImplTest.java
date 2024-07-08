package br.com.app.cleanarchitecture.application.impl;

import br.com.app.cleanarchitecture.application.dto.request.UserRequest;
import br.com.app.cleanarchitecture.application.dto.response.UserResponse;
import br.com.app.cleanarchitecture.application.exception.BusinessException;
import br.com.app.cleanarchitecture.domain.gateway.UserGateway;
import br.com.app.cleanarchitecture.domain.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;


@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Test
    void givenValidUserRequest_whenCreateUser_thenUserIsSuccessfullyCreated() {
        // Given
        UserRequest userRequest = new UserRequest(null, "testUser", "test@example.com", "password", "Test User", "ROLE_USER",true);
        UserModel userModel = new UserModel(null, "testUser", "test@example.com", "password", "Test User", "ROLE_USER", true);
        when(userGateway.findByEmail(anyString())).thenReturn(false);
        when(userGateway.create(any(UserModel.class))).thenReturn(userModel);

        // When
        UserResponse userResponse = createUserUseCase.execute(userRequest);

        // Then
        assertNotNull(userResponse);
        assertEquals("testUser", userResponse.getUsername());
        verify(userGateway, times(1)).create(any(UserModel.class));
    }

    @Test
    void givenUserRequestWithExistingEmail_whenCreateUser_thenThrowsBusinessException() {
        // Given
        UserRequest userRequest = new UserRequest(null, "testUser", "test@example.com", "password", "Test User", "ROLE_USER", true);
        when(userGateway.findByEmail(anyString())).thenReturn(true);

        // When
        Exception exception = assertThrows(BusinessException.class, () -> {
            createUserUseCase.execute(userRequest);
        });

        // Then
        assertEquals("Email already exists.", exception.getMessage());
        verify(userGateway, never()).create(any(UserModel.class));
    }
}