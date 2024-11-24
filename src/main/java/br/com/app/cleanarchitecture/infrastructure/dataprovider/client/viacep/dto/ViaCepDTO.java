package br.com.app.cleanarchitecture.infrastructure.dataprovider.client.viacep.dto;

import lombok.Getter;

@Getter
public class ViaCepDTO {
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