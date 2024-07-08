package br.com.app.cleanarchitecture.application.impl;

import br.com.app.cleanarchitecture.application.DeleteUserUseCase;
import br.com.app.cleanarchitecture.application.exception.EntityNotFoundException;
import br.com.app.cleanarchitecture.domain.gateway.UserGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private static final Logger logger = LoggerFactory.getLogger(DeleteUserUseCaseImpl.class);
    private final UserGateway userGateway;

    public DeleteUserUseCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void execute(Long id) {
        userGateway.findById(id)
                .ifPresentOrElse(
                        user -> {
                            userGateway.delete(id);
                            logger.info("Successfully deleted user with ID: {}", id);
                        },
                        () -> {
                            String errorMessage = String.format("User with ID %d not found, cannot delete.", id);
                            logger.error(errorMessage);
                            throw new EntityNotFoundException(id);
                        }
                );
    }
}
