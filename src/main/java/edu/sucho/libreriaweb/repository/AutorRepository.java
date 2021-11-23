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

    @Query(value = "CALL lsp_cambiar_estado_autor(:id,:status);", nativeQuery = true)
    String changeStatus(@Param("id") int id, @Param("status") Boolean estado);
}
