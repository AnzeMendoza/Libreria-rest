package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.entity.Prestamo;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.repository.query.Param;

public interface PrestamoRepository extends BaseRepository<Prestamo, Integer> {

    @Query(value = "SELECT * FROM prestamo WHERE prestamo.alta = true", nativeQuery = true)
    List<Prestamo> findAllByAlta();

    @Query(value = "SELECT * FROM prestamo WHERE prestamo.alta = true AND fk_cliente=fk_cliente", nativeQuery = true)
    List<Prestamo> findAllByIdClienteAndAlta(@Param("fk_cliente") Integer fk_cliente);

    @Query(value = "CALL lsp_cambiar_estado_prestamo(:id,:alta);", nativeQuery = true)
    String changeStatusSp(@Param("id") int id, @Param("alta") Boolean alta);

    @Query(value = "CALL lsp_crear_prestamo(:fk_cliente, :fecha_devolucion, :fecha_prestamo, :fk_libro)",
            nativeQuery = true)
    String createSp(
            @Param("fk_cliente") int fk_cliente,
            @Param("fecha_devolucion") Date fecha_devolucion,
            @Param("fecha_prestamo") Date fecha_prestamo,
            @Param("fk_libro") int fk_libro
    );

    @Query(value = "CALL lsp_modificar_prestamo(:id,:fecha_devolucion,:fecha_prestamo,:fk_cliente,"
            + ":fk_libro);", nativeQuery = true)
    String updateSp(@Param("id") Integer id,
                    @Param("fecha_devolucion") Date fecha_devolucion,
                    @Param("fecha_prestamo") Date fecha_prestamo,
                    @Param("fk_cliente") Integer fk_cliente,
                    @Param("fk_libro") Integer fk_libro);

}
