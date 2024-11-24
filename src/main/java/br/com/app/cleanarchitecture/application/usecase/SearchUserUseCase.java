package br.com.app.cleanarchitecture.application.usecase;

import br.com.app.cleanarchitecture.application.dto.response.UserResponse;

import java.util.List;

public interface SearchUserUseCase {

    List<UserResponse> execute();
}
