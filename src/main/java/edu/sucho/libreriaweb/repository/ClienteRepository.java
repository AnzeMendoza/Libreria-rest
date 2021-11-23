package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.Cliente;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends BaseRepository<Cliente, Integer>{
    @Query(value = "SELECT * FROM cliente WHERE cliente.alta = true", nativeQuery = true)
    List<Cliente> findAllByAlta();
     @Query(value = "SELECT * FROM editorial WHERE cleinte.documento =:documento ", nativeQuery = true)
    Cliente findByValueField(@Param("documento")String documento);
     @Query(value="CALL lsp_cambiar_estado_cliente(:id,:status);" , nativeQuery = true)
    String changeStatus(@Param("id")int id ,@Param("status")Boolean estado);
     @Query(value = "CALL lsp_crear_cliente(:documento , :nombre , :apellido , :telefono)", nativeQuery = true)
    String saveCliente(@Param("documento")Long documento ,@Param("nombre")String nombre,@Param("apellido")String apellido,@Param("telefono")String telefono);
    @Query(value="CALL lsp_modificar_cliente(:id, :documento , :nombre , :apellido , :telefono)" , nativeQuery = true)
    String updateCliente(@Param("id")Integer id ,@Param("documento")Long documento ,@Param("nombre")String nombre,@Param("apellido")String apellido,@Param("telefono")String telefono);

}

