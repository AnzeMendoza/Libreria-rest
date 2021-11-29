package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.Prestamo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.data.repository.query.Param;

public interface PrestamoRepository extends BaseRepository<Prestamo, Integer>{
    @Query(value = "SELECT * FROM prestamo WHERE prestamo.alta = true", nativeQuery = true)
    List<Prestamo> findAllByAlta();
    
    @Query(value = "CALL lsp_cambiar_estado_prestamo(:id,:alta);", nativeQuery = true)
    String changeStatusSp(@Param("id") int id, @Param("alta") Boolean alta);

}

