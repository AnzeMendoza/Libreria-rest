package edu.sucho.libreriaweb.repository;


import edu.sucho.libreriaweb.model.entity.Rol;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository  extends BaseRepository<Rol, Integer> {
    Optional<Rol> findByType(String trype);
}
