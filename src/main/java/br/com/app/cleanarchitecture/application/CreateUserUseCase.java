package br.com.app.cleanarchitecture.application;

import br.com.app.cleanarchitecture.application.dto.request.UserRequest;
import br.com.app.cleanarchitecture.application.dto.response.UserResponse;

public interface CreateUserUseCase {

    UserResponse execute(UserRequest request);
}
