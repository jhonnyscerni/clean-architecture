package br.com.app.cleanarchitecture.infrastructure.dataprovider.client.viacep;

import br.com.app.cleanarchitecture.infrastructure.dataprovider.client.config.ClientConfig;
import br.com.app.cleanarchitecture.infrastructure.dataprovider.client.viacep.dto.ViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws", configuration = ClientConfig.class)
public interface ViaCepClient {

    @GetMapping("/{cep}/json")
    ViaCepDTO getCepInfo(@PathVariable("cep") String cep);
}
