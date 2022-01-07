package edu.sucho.libreriaweb.mapper;


import edu.sucho.libreriaweb.model.entity.Usuario;
import edu.sucho.libreriaweb.web.presentation.AuthorizationRequest;
import edu.sucho.libreriaweb.web.presentation.UserResponse;
import org.springframework.security.core.userdetails.User;

public class UserMapper {

	private UserMapper() {
	}

	public static UserResponse toResponse(Usuario user) {
		return UserResponse.builder().name(user.getUsername()).id(user.getId()).build();
	}

	public static Usuario toDomain(AuthorizationRequest authorizationRequest) {
		return Usuario.builder().username(authorizationRequest.getUserName()).userPassword(authorizationRequest.getPassword())
				.build();
	}
}
