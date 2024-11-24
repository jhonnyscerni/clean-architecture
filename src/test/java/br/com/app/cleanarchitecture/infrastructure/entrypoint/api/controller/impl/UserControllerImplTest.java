package br.com.app.cleanarchitecture.infrastructure.entrypoint.api.controller.impl;

import br.com.app.cleanarchitecture.application.dto.request.UserRequest;
import br.com.app.cleanarchitecture.application.dto.response.UserResponse;
import br.com.app.cleanarchitecture.application.exception.EntityNotFoundException;
import br.com.app.cleanarchitecture.application.usecase.impl.*;
import br.com.app.cleanarchitecture.infrastructure.exception.handler.ApiExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
class UserControllerImplTest {

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private SearchUserUseCaseImpl searchUserUseCase;

    @Mock
    private GetUserUseCaseImpl getUserUseCase;

    @Mock
    private CreateUserUseCaseImpl createUserUseCase;

    @Mock
    private UpdateUserUseCaseImpl updateUserUseCase;

    @Mock
    private DeleteUserUseCaseImpl deleteUserUseCase;

    @BeforeEach
    void setUp() {
        UserControllerImpl userControllerImpl = new UserControllerImpl(searchUserUseCase, getUserUseCase, createUserUseCase, updateUserUseCase, deleteUserUseCase);


        this.mockMvc = MockMvcBuilders.standaloneSetup(userControllerImpl)
                .setControllerAdvice(new ApiExceptionHandler())
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void givenUsersExist_whenSearchIsCalled_thenShouldReturnUsers() throws Exception {
        // Given
        List<UserResponse> expectedUsers = List.of(
                new UserResponse(1L, "userOne", "userOne@example.com", "User One", "ROLE_USER", true),
                new UserResponse(2L, "userTwo", "userTwo@example.com", "User Two", "ROLE_ADMIN", true)
        );
        when(searchUserUseCase.execute()).thenReturn(expectedUsers);

        // When & Then
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].username", is("userOne")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].username", is("userTwo")));
    }

    @Test
    void givenUserIdExists_whenGetUserIsCalled_thenShouldReturnUser() throws Exception {
        // Given
        Long userId = 1L;
        UserResponse expectedUser = new UserResponse(userId, "userOne", "userOne@example.com", "User One", "ROLE_USER", true);
        when(getUserUseCase.execute(userId)).thenReturn(expectedUser);

        // When & Then
        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(userId.intValue())))
                .andExpect(jsonPath("$.username", is("userOne")))
                .andExpect(jsonPath("$.email", is("userOne@example.com")))
                .andExpect(jsonPath("$.name", is("User One")))
                .andExpect(jsonPath("$.role", is("ROLE_USER")))
                .andExpect(jsonPath("$.enabled", is(true)));
    }

    @Test
    void givenValidUserRequest_whenCreateUserIsCalled_thenShouldReturnCreatedUser() throws Exception {
        // Given
        UserRequest userRequest = new UserRequest(null, "newUser", "newUser@example.com", "password", "New User", "ROLE_USER", true);
        UserResponse expectedUser = new UserResponse(1L, "newUser", "newUser@example.com", "New User", "ROLE_USER", true);
        when(createUserUseCase.execute(Mockito.any(UserRequest.class))).thenReturn(expectedUser);

        // When & Then
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("newUser")))
                .andExpect(jsonPath("$.email", is("newUser@example.com")))
                .andExpect(jsonPath("$.name", is("New User")))
                .andExpect(jsonPath("$.role", is("ROLE_USER")))
                .andExpect(jsonPath("$.enabled", is(true)));
    }

    @Test
    void givenInvalidUserRequest_whenCreateUserIsCalled_thenShouldReturnBadRequest() throws Exception {
        // Given
        UserRequest invalidUserRequest = new UserRequest(null, "", "", "", "", "", false);

        // When & Then
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUserRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenValidUserRequest_whenUpdateUserIsCalled_thenShouldReturnUpdatedUser() throws Exception {
        // Given
        Long userId = 1L;
        UserRequest userRequest = new UserRequest(userId, "existingUser", "existingUser@example.com", "password", "Existing User", "ROLE_USER", true);
        UserResponse expectedUser = new UserResponse(1L, "editUser", "editUser@example.com", "Edit User", "ROLE_USER", true);

        when(updateUserUseCase.execute(Mockito.anyLong(), Mockito.any(UserRequest.class))).thenReturn(expectedUser);

        // When & Then
        mockMvc.perform(put("/users/"+userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("editUser")))
                .andExpect(jsonPath("$.email", is("editUser@example.com")))
                .andExpect(jsonPath("$.name", is("Edit User")))
                .andExpect(jsonPath("$.role", is("ROLE_USER")))
                .andExpect(jsonPath("$.enabled", is(true)));
    }
    @Test
    void givenInvalidUserRequest_whenUpdateUserIsCalled_thenShouldReturnBadRequest() throws Exception {
        // Given
        Long userId = 1L;
        UserRequest invalidUserRequest = new UserRequest(null, "", "", "", "", "", false);

        // When & Then
        when(updateUserUseCase.execute(eq(userId), any(UserRequest.class)))
                .thenThrow(new EntityNotFoundException(userId));

        mockMvc.perform(put("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUserRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    void givenUserIdExists_whenDeleteUserIsCalled_thenShouldReturnNoContent() throws Exception {
        // Given
        Long userId = 1L;
        doNothing().when(deleteUserUseCase).execute(userId);

        // When
        mockMvc.perform(delete("/users/{id}", userId))
                .andExpect(status().isNoContent());

        // Then
        verify(deleteUserUseCase, times(1)).execute(userId); //
    }

    @Test
    void givenUserIdDoesNotExist_whenDeleteUserIsCalled_thenShouldReturnNotFound() throws Exception {
        // Given
        Long nonExistentUserId = 999L;
        doThrow(new EntityNotFoundException(nonExistentUserId))
                .when(deleteUserUseCase).execute(nonExistentUserId);

        // When & Then
        mockMvc.perform(delete("/users/{id}", nonExistentUserId))
                .andExpect(status().isNotFound());

        verify(deleteUserUseCase, times(1)).execute(nonExistentUserId);
    }

}