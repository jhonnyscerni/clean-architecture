package br.com.app.cleanarchitecture.application.impl;

import br.com.app.cleanarchitecture.application.UpdateUserUseCase;
import br.com.app.cleanarchitecture.application.dto.request.UserRequest;
import br.com.app.cleanarchitecture.application.dto.response.UserResponse;
import br.com.app.cleanarchitecture.application.exception.EntityNotFoundException;
import br.com.app.cleanarchitecture.application.mapper.UserModelMapper;
import br.com.app.cleanarchitecture.domain.gateway.UserGateway;
import br.com.app.cleanarchitecture.domain.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserGateway userGateway;

    public UpdateUserUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public UserResponse execute(Long id, UserRequest request) {
        UserModel userModel = userGateway.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        UserModelMapper.synchronizeUserModelWithRequest(userModel, request);
        UserModel updatedUserModel = userGateway.update(userModel);
        return UserModelMapper.fromUserModelToResponse(updatedUserModel);
    }

}
