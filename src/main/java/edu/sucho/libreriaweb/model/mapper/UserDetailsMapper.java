package edu.sucho.libreriaweb.model.mapper;

import edu.sucho.libreriaweb.model.entity.Cliente;
import edu.sucho.libreriaweb.model.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDetailsMapper {
    public UserDetails build(Cliente user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }

    private Set<? extends GrantedAuthority> getAuthorities(Cliente retrievedUser) {

        Set<Role> roles=new HashSet<>();
        roles.add(retrievedUser.getRole());

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));

        return authorities;

    }

}
