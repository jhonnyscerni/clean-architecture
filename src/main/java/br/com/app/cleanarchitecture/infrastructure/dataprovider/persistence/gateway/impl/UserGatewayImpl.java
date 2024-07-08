package br.com.app.cleanarchitecture.infrastructure.dataprovider.persistence.gateway.impl;

import br.com.app.cleanarchitecture.infrastructure.mapper.UserMapper;
import br.com.app.cleanarchitecture.infrastructure.dataprovider.persistence.entity.UserEntity;
import br.com.app.cleanarchitecture.infrastructure.dataprovider.persistence.repository.UserRepository;
import br.com.app.cleanarchitecture.domain.gateway.UserGateway;
import br.com.app.cleanarchitecture.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserModel create(UserModel userModel) {
        UserEntity userEntity = userMapper.userModelToEntity(userModel);
        UserEntity savedEntity = userRepository.save(userEntity);
        return userMapper.entityToUserModel(savedEntity);
    }

    @Override
    public UserModel update(UserModel userModel) {
        UserEntity userEntity = userMapper.userModelToEntity(userModel);
        UserEntity updatedEntity = userRepository.save(userEntity);
        return userMapper.entityToUserModel(updatedEntity);
    }

    @Override
    public Optional<UserModel> findById(Long id) {
        return userRepository.findById(id).map(userMapper::entityToUserModel);
    }

    @Override
    public boolean findByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<UserModel> search() {
        return this.userRepository.findAll().stream()
                .map(userMapper::entityToUserModel)
                .collect(Collectors.toList());
    }
}
