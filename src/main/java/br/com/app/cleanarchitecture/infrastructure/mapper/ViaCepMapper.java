package br.com.app.cleanarchitecture.infrastructure.mapper;


import br.com.app.cleanarchitecture.domain.model.ViaCepModel;
import br.com.app.cleanarchitecture.infrastructure.dataprovider.client.viacep.dto.ViaCepDTO;
import org.springframework.stereotype.Component;

@Component
public class ViaCepMapper {

    public ViaCepModel responseToModel(ViaCepDTO response) {
        if (response == null) {
            return null;
        }
        return new ViaCepModel(
                response.getCep(),
                response.getLogradouro(),
                response.getComplemento(),
                response.getBairro(),
                response.getLocalidade(),
                response.getUf(),
                response.getIbge(),
                response.getGia(),
                response.getDdd(),
                response.getSiafi()
        );
    }

}
