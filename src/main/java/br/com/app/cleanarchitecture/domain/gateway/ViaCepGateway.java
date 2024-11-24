package br.com.app.cleanarchitecture.domain.gateway;

import br.com.app.cleanarchitecture.domain.model.ViaCepModel;

public interface ViaCepGateway {

    ViaCepModel getCepInfo(String cep);
}
