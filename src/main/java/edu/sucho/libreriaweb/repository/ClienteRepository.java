package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.Cliente;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends BaseRepository<Cliente, Integer>{
    @Query(value = "SELECT * FROM cliente WHERE cliente.alta = true", nativeQuery = true)
    List<Cliente> findAllByAlta();
}
