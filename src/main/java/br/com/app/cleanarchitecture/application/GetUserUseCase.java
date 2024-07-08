package br.com.app.cleanarchitecture.application;

import br.com.app.cleanarchitecture.application.dto.response.UserResponse;

public interface GetUserUseCase {

    UserResponse execute(Long id);
}
