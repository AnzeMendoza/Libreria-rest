package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.Prestamo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrestamoRepository extends BaseRepository<Prestamo, Integer>{

    @Query(value = "SELECT * FROM prestamo WHERE prestamo.alta = true", nativeQuery = true)
    List<Prestamo> findAllByAlta();

    //Todo crearSp



    //Todo modificarSp

    //Todo activar/desactivar

}

