package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.Autor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends BaseRepository<Autor, Integer> {
    @Query(value = "SELECT * FROM autor WHERE autor.alta = true", nativeQuery = true)
    List<Autor> findAllByAlta();

    @Query(value = "CALL lsp_cambiar_estado_autor(:id,:alta);", nativeQuery = true)
    String changeStatusSp(@Param("id") int id, @Param("alta") Boolean estado);

    @Query(value = "CALL lsp_crear_autor(:nombre)", nativeQuery = true)
    String createSp(@Param("nombre") String nombre);

    @Query(value = "CALL lsp_modificar_autor(:id,:nombre);", nativeQuery = true)
    String updateSp(@Param("id") Integer id, @Param("nombre") String nombre);
}
