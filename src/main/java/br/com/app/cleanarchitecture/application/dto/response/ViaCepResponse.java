package br.com.app.cleanarchitecture.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ViaCepResponse {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

}