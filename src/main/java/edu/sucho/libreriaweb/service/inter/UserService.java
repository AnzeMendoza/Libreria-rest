package edu.sucho.libreriaweb.service.inter;

import edu.sucho.libreriaweb.model.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	Usuario getUsuario(Integer id);

}
