package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.entity.Editorial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

@Repository
public interface EditorialRepository extends BaseRepository<Editorial, Integer> {

    @Query(value = "SELECT * FROM editorial where alta=true", nativeQuery = true)
    List<Editorial> findAllByAlta();

    @Query(value = "SELECT id,alta,nombre FROM editorial WHERE editorial.nombre =:name", nativeQuery = true)
    Editorial findByValueField(@Param("name") String name);

    @Query(value = "CALL lsp_cambiar_estado_editorial(:id,:status);", nativeQuery = true)
    String changeStatus(@Param("id") int id, @Param("status") Boolean estado);

    @Query(value = "CALL lsp_crear_editorial(:name)", nativeQuery = true)
    String saveEditorial(@Param("name") String name);

    @Query(value = "CALL lsp_modificar_editorial(:id,:name);", nativeQuery = true)
    String updateEditorial(@Param("id") Integer id, @Param("name") String name);

    @Query(value = "call lsp_buscar_por_patron_nombre_editorial(:string )", nativeQuery = true)
    List<Editorial> findEditorialForPatternName(@Param("string") String string);


}
