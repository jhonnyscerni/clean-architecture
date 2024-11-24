package br.com.app.cleanarchitecture.application.mapper;

import br.com.app.cleanarchitecture.application.dto.response.ViaCepResponse;
import br.com.app.cleanarchitecture.domain.model.ViaCepModel;

public class ViaCepModelMapper {

    public static ViaCepResponse mapToResponse(ViaCepModel viaCepModel) {
        return ViaCepResponse.builder()
                .cep(viaCepModel.getCep())
                .logradouro(viaCepModel.getLogradouro())
                .complemento(viaCepModel.getComplemento())
                .bairro(viaCepModel.getBairro())
                .localidade(viaCepModel.getLocalidade())
                .uf(viaCepModel.getUf())
                .ibge(viaCepModel.getIbge())
                .gia(viaCepModel.getGia())
                .ddd(viaCepModel.getDdd())
                .siafi(viaCepModel.getSiafi())
                .build();
    }
}