package br.com.app.cleanarchitecture.infrastructure.entrypoint.api.controller.impl;

import br.com.app.cleanarchitecture.application.dto.request.UserRequest;
import br.com.app.cleanarchitecture.application.dto.response.UserResponse;
import br.com.app.cleanarchitecture.application.usecase.*;
import br.com.app.cleanarchitecture.infrastructure.entrypoint.api.controller.UserController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final SearchUserUseCase searchUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @GetMapping
    public ResponseEntity<List<UserResponse>> search() {
        return ResponseEntity.ok(searchUserUseCase.execute());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long userId) {
        UserResponse userResponse = getUserUseCase.execute(userId);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = createUserUseCase.execute(userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@Valid @PathVariable Long userId, @RequestBody UserRequest userRequest) {
        UserResponse userResponse = updateUserUseCase.execute(userId, userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        deleteUserUseCase.execute(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
