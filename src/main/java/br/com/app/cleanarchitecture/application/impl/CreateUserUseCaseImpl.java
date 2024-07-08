package br.com.app.cleanarchitecture.application.impl;

import br.com.app.cleanarchitecture.application.CreateUserUseCase;
import br.com.app.cleanarchitecture.application.dto.request.UserRequest;
import br.com.app.cleanarchitecture.application.dto.response.UserResponse;
import br.com.app.cleanarchitecture.application.mapper.UserModelMapper;
import br.com.app.cleanarchitecture.application.exception.BusinessException;
import br.com.app.cleanarchitecture.domain.gateway.UserGateway;
import br.com.app.cleanarchitecture.domain.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CreateUserUseCaseImpl.class);
    private final UserGateway userGateway;

    public CreateUserUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    @Transactional
    public UserResponse execute(UserRequest request) {
        logger.info("Validating email uniqueness for: {}", request.getEmail());
        validateEmailUniqueness(request.getEmail());

        logger.info("Creating user from request");
        UserModel newUserModel = userGateway.create(UserModelMapper.requestToUserModel(request));

        logger.info("Converting User to UserResponse");
        return UserModelMapper.fromUserModelToResponse(newUserModel);
    }

    private void validateEmailUniqueness(String email) {
        Optional.ofNullable(email)
                .filter(userGateway::findByEmail)
                .ifPresent(e -> {
                    logger.error("Email already exists: {}", e);
                    throw new BusinessException("Email already exists.");
                });
    }
}
