package br.com.app.cleanarchitecture.domain.gateway;

import br.com.app.cleanarchitecture.domain.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserGateway {

    UserModel create(UserModel userModel);

    UserModel update(UserModel userModel);

    Optional<UserModel> findById(Long id);

    boolean findByEmail(String email);

    void delete(Long id);

    List<UserModel> search();

}
