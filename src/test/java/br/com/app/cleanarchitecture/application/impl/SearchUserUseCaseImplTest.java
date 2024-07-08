package br.com.app.cleanarchitecture.application.impl;


import br.com.app.cleanarchitecture.application.dto.response.UserResponse;
import br.com.app.cleanarchitecture.domain.gateway.UserGateway;
import br.com.app.cleanarchitecture.domain.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchUserUseCaseImplTest {

    @Mock
    private UserGateway userGateway;

    @InjectMocks
    private SearchUserUseCaseImpl searchUserUseCase;

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenValidCriteria_whenSearchUsers_thenUsersAreSuccessfullyReturned() {
        // Given
        List<UserModel> expectedUsers = Collections.singletonList(
                new UserModel(1L, "testUser", "test@example.com", "password", "Test User", "ROLE_USER", true)
        );
        when(userGateway.search()).thenReturn(expectedUsers);

        // When
        List<UserResponse> actualUsers = searchUserUseCase.execute();

        // Then
        assertEquals(expectedUsers.size(), actualUsers.size());
        assertEquals(expectedUsers.get(0).getUsername(), actualUsers.get(0).getUsername());
    }

    @Test
    void givenInvalidCriteria_whenSearchUsers_thenNoUsersAreReturned() {
        // Given
        when(userGateway.search()).thenReturn(Collections.emptyList());

        // When
        List<UserResponse> actualUsers = searchUserUseCase.execute();

        // Then
        assertEquals(0, actualUsers.size());
    }
}