package br.com.app.cleanarchitecture.infrastructure.config.usecase;

import br.com.app.cleanarchitecture.application.*;
import br.com.app.cleanarchitecture.application.impl.*;
import br.com.app.cleanarchitecture.domain.gateway.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseConfigBean {

    private final UserGateway userGateway;

    public UserUseCaseConfigBean(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Bean
    public CreateUserUseCase createCustomerUseCase() {
        return new CreateUserUseCaseImpl(userGateway);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase() {
        return new UpdateUserUseCaseImpl(userGateway);
    }

    @Bean
    public GetUserUseCase getUserUseCase() {
        return new GetUserUseCaseImpl(userGateway);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase() {
        return new DeleteUserUseCaseImpl(userGateway);
    }

    @Bean
    public SearchUserUseCase createUserUseCase() {
        return new SearchUserUseCaseImpl(userGateway);
    }
}
