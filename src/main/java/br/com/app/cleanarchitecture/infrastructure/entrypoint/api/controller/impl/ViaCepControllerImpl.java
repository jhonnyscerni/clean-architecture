package br.com.app.cleanarchitecture.infrastructure.entrypoint.api.controller.impl;

import br.com.app.cleanarchitecture.application.usecase.GetCepInfoUseCase;
import br.com.app.cleanarchitecture.application.dto.response.ViaCepResponse;
import br.com.app.cleanarchitecture.infrastructure.entrypoint.api.controller.ViaCepController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViaCepControllerImpl implements ViaCepController {

    private final GetCepInfoUseCase getCepInfoUseCase;

    public ViaCepControllerImpl(GetCepInfoUseCase getCepInfoUseCase) {
        this.getCepInfoUseCase = getCepInfoUseCase;
    }

    @Override
    public ResponseEntity<ViaCepResponse> getCepInfo(String cep) {
        ViaCepResponse response = getCepInfoUseCase.execute(cep);
        return ResponseEntity.ok(response);
    }
}