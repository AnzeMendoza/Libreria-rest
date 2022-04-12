package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.entity.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends BaseRepository<Autor, Integer> {
    @Query(value = "SELECT * FROM autor WHERE autor.alta = true", nativeQuery = true)
    List<Autor> findAllByAlta();
    
    @Query(value = "select * from fn_cambiar_estado_autor(:id,:alta);", nativeQuery = true)
    String changeStatusSp(@Param("id") int id, @Param("alta") Boolean estado);

    @Query(value = "select * from fn_crear_autor(:nombre)", nativeQuery = true)
    String createSp(@Param("nombre") String nombre);

    @Query(value = "select * from fn_modificar_autor(:id,:nombre);", nativeQuery = true)
    String updateSp(@Param("id") Integer id, @Param("nombre") String nombre);
    
    @Query(value = "SELECT id,alta,nombre FROM autor WHERE autor.nombre =:name", nativeQuery = true)
    Autor findByValueField(@Param("name")String name);
    
    @Query(value = "select * from fn_buscar_por_patron_nombre(:string)", nativeQuery = true)
    List<Autor> findAutorForPatternName(@Param("string") String string);

    @Query(
            value = "select * from autor;",
            countQuery = "select count(*) from autor;",
            nativeQuery = true
    )
    Page<Autor> getAll(Pageable pageable);
}
