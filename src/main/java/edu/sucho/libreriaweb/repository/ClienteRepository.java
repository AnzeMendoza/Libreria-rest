 package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends BaseRepository<Cliente, Integer> {
    @Query(value = "SELECT * FROM cliente WHERE cliente.alta = true", nativeQuery = true)
    List<Cliente> findAllByAlta();

    @Query(value = "SELECT * FROM editorial WHERE cleinte.documento =:documento ", nativeQuery = true)
    Cliente findByValueField(@Param("documento") String documento);

    @Query(value = "CALL lsp_cambiar_estado_cliente(:id,:status);", nativeQuery = true)
    String changeStatus(@Param("id") int id, @Param("status") Boolean estado);

    @Query(value = "CALL lsp_crear_cliente(:documento , :nombre , :apellido , :telefono, :username, :password, :roleId)", nativeQuery = true)
    String saveCliente(@Param("documento") Long documento,
                       @Param("nombre") String nombre,
                       @Param("apellido") String apellido,
                       @Param("telefono") String telefono,
                       @Param("username") String username,
                       @Param("password") String password,
                       @Param("roleId") int roleId);


    @Query(value = "CALL lsp_modificar_cliente(:id, :documento , :nombre , :apellido , :telefono)", nativeQuery = true)
    String updateCliente(@Param("id") Integer id,
                         @Param("documento") Long documento,
                         @Param("nombre") String nombre,
                         @Param("apellido") String apellido,
                         @Param("telefono") String telefono);

    @Query(value = "SELECT * FROM cliente where nombre like :string", nativeQuery = true)
    List<Cliente> findClienteForPatternName(@Param("string") String string);

}

