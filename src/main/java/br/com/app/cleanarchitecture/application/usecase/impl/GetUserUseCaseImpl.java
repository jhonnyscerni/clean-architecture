package br.com.app.cleanarchitecture.application.usecase.impl;

import br.com.app.cleanarchitecture.application.usecase.GetUserUseCase;
import br.com.app.cleanarchitecture.application.dto.response.UserResponse;
import br.com.app.cleanarchitecture.application.mapper.UserModelMapper;
import br.com.app.cleanarchitecture.application.exception.EntityNotFoundException;
import br.com.app.cleanarchitecture.domain.gateway.UserGateway;
import org.springframework.stereotype.Component;

@Component
public class GetUserUseCaseImpl implements GetUserUseCase {

    private final UserGateway userGateway;

    public GetUserUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public UserResponse execute(Long id) {

        return userGateway.findById(id)
                .map(UserModelMapper::fromUserModelToResponse)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }
}
