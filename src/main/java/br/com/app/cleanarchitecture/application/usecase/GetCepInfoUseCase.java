package br.com.app.cleanarchitecture.application.usecase;

import br.com.app.cleanarchitecture.application.dto.response.ViaCepResponse;

public interface GetCepInfoUseCase {
    ViaCepResponse execute(String cep);
}
