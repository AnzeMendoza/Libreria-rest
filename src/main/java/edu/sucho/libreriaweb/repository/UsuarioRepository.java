package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends BaseRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);
}
