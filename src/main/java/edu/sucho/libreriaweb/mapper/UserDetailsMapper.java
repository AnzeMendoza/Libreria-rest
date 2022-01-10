package edu.sucho.libreriaweb.mapper;


import edu.sucho.libreriaweb.model.entity.Cliente;
import edu.sucho.libreriaweb.model.entity.Rol;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class 	UserDetailsMapper {

	public static UserDetails build(Cliente cliente) {
		return new org.springframework.security.core.userdetails.User(cliente.getUsername(), cliente.getPassword(), getAuthorities(cliente));
	}

	private static Set<? extends GrantedAuthority> getAuthorities(Cliente retrievedUser) {
		Rol rol = retrievedUser.getRol();

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		 authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getType()));

		return authorities;
	}
}
