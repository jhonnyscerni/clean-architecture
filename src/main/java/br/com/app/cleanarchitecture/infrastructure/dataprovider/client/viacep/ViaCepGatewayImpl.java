package br.com.app.cleanarchitecture.infrastructure.dataprovider.client.viacep;

import br.com.app.cleanarchitecture.domain.gateway.ViaCepGateway;
import br.com.app.cleanarchitecture.domain.model.ViaCepModel;
import br.com.app.cleanarchitecture.infrastructure.mapper.ViaCepMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViaCepGatewayImpl implements ViaCepGateway {

    private final ViaCepClient viaCepClient;
    private final ViaCepMapper viaCepMapper;

    @Override
    public ViaCepModel getCepInfo(String cep) {
        return viaCepMapper.responseToModel(viaCepClient.getCepInfo(cep));
    }
}
