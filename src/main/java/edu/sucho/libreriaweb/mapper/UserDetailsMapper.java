package edu.sucho.libreriaweb.mapper;


import edu.sucho.libreriaweb.model.entity.Rol;
import edu.sucho.libreriaweb.model.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

public class 	UserDetailsMapper {

	public static UserDetails build(Usuario user) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getUserPassword(), getAuthorities(user));
	}

	private static Set<? extends GrantedAuthority> getAuthorities(Usuario retrievedUser) {
		Rol rol = retrievedUser.getRol();

		Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		 authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getType()));

		return authorities;
	}
}
