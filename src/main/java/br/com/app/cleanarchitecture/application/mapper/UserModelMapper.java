package br.com.app.cleanarchitecture.application.mapper;

import br.com.app.cleanarchitecture.application.dto.request.UserRequest;
import br.com.app.cleanarchitecture.application.dto.response.UserResponse;
import br.com.app.cleanarchitecture.domain.model.UserModel;

public class UserModelMapper {

    public static UserModel requestToUserModel(UserRequest request) {
        return new UserModel(
                request.getId(),
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                request.getName(),
                request.getRole(),
                request.isEnabled()
        );
    }

    public static void synchronizeUserModelWithRequest(UserModel userModel, UserRequest request) {
        userModel.setId(request.getId());
        userModel.setName(request.getName());
        userModel.setEmail(request.getEmail());
        userModel.setUsername(request.getUsername());
        userModel.setPassword(request.getPassword());
        userModel.setRole(request.getRole());
        userModel.setEnabled(request.isEnabled());
    }

    public static UserResponse fromUserModelToResponse(UserModel userModel) {
        return new UserResponse(
                userModel.getId(),
                userModel.getUsername(),
                userModel.getEmail(),
                userModel.getPassword(),
                userModel.getName(),
                userModel.getRole(),
                userModel.isEnabled()
        );
    }
}