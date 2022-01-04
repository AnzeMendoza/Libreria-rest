package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.entity.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);
}
