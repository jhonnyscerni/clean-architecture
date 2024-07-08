package br.com.app.cleanarchitecture.infrastructure.entrypoint.api.controller;

import br.com.app.cleanarchitecture.application.dto.request.UserRequest;
import br.com.app.cleanarchitecture.application.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "User Management", description = "Operations related to user management")
public interface UserController {

    @Operation(summary = "Search all users", description = "Returns a list of users")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserResponse.class)))
    ResponseEntity<List<UserResponse>> search();

    @Operation(summary = "Find user by ID", description = "Returns a single user details by ID")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserResponse.class)))
    ResponseEntity<UserResponse> findUserById(Long userId);

    @Operation(summary = "Create a new user", description = "Creates a new user and returns the created user details")
    @ApiResponse(responseCode = "201", description = "User created successfully",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserResponse.class))})
    ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest);

    @Operation(summary = "Update a user", description = "Updates an existing user's details and returns the updated user information")
    @ApiResponse(responseCode = "200", description = "User updated successfully",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserResponse.class))})
    ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest);

    @Operation(summary = "Delete a user", description = "Deletes an existing user by their ID")
    @ApiResponse(responseCode = "204", description = "User deleted successfully",
            content = @Content)
    ResponseEntity<Void> deleteUser(Long userId);
}
