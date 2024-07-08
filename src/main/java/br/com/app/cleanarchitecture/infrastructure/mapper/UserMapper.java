package br.com.app.cleanarchitecture.infrastructure.mapper;

import br.com.app.cleanarchitecture.infrastructure.dataprovider.persistence.entity.UserEntity;
import br.com.app.cleanarchitecture.domain.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity userModelToEntity(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        return UserEntity.builder()
                .id(userModel.getId())
                .username(userModel.getUsername())
                .email(userModel.getEmail())
                .password(userModel.getPassword())
                .name(userModel.getName())
                .role(userModel.getRole())
                .enabled(userModel.isEnabled())
                .build();
    }

    public UserModel entityToUserModel(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return new UserModel(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getName(),
                userEntity.getRole(),
                userEntity.isEnabled()
        );
    }
}