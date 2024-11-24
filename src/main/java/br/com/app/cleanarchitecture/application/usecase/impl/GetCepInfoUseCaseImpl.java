package br.com.app.cleanarchitecture.application.usecase.impl;

import br.com.app.cleanarchitecture.application.usecase.GetCepInfoUseCase;
import br.com.app.cleanarchitecture.application.dto.response.ViaCepResponse;
import br.com.app.cleanarchitecture.application.mapper.ViaCepModelMapper;
import br.com.app.cleanarchitecture.domain.gateway.ViaCepGateway;
import org.springframework.stereotype.Component;

@Component
public class GetCepInfoUseCaseImpl implements GetCepInfoUseCase {

    private final ViaCepGateway viaCepGateway;

    public GetCepInfoUseCaseImpl(ViaCepGateway viaCepGateway) {
        this.viaCepGateway = viaCepGateway;
    }

    @Override
    public ViaCepResponse execute(String cep) {
        return ViaCepModelMapper.mapToResponse(viaCepGateway.getCepInfo(cep));
    }
}
