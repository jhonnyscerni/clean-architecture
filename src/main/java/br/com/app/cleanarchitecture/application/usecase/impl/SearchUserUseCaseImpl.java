package br.com.app.cleanarchitecture.application.usecase.impl;

import br.com.app.cleanarchitecture.application.usecase.SearchUserUseCase;
import br.com.app.cleanarchitecture.application.dto.response.UserResponse;
import br.com.app.cleanarchitecture.application.mapper.UserModelMapper;
import br.com.app.cleanarchitecture.domain.gateway.UserGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchUserUseCaseImpl implements SearchUserUseCase {

    private final UserGateway userGateway;

    public SearchUserUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<UserResponse> execute() {
        return userGateway.search().stream()
                .map(UserModelMapper::fromUserModelToResponse)
                .collect(Collectors.toList());
    }
}
