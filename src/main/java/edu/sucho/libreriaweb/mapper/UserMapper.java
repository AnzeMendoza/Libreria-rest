package edu.sucho.libreriaweb.mapper;

import edu.sucho.libreriaweb.model.entity.Cliente;
import edu.sucho.libreriaweb.web.presentation.AuthorizationRequest;
import edu.sucho.libreriaweb.web.presentation.UserResponse;

public class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toResponse(Cliente cliente) {
        return UserResponse.builder().name(cliente.getUsername()).id(cliente.getId()).build();
    }

    public static Cliente toDomain(AuthorizationRequest authorizationRequest) {
        return Cliente.builder()
                .username(authorizationRequest.getUsername())
                .password(authorizationRequest.getPassword())
                .build();
    }
}
