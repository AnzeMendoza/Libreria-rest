package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.Editorial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.repository.query.Param;

@Repository
public interface EditorialRepository extends BaseRepository<Editorial, Integer> {
    @Query(value = "SELECT * FROM editorial WHERE editorial.alta = true", nativeQuery = true)
    List<Editorial> findAllByAlta();

    Editorial findByNombre(String nombre);
    
   @Query(value = "SELECT * FROM editorial WHERE editorial.nombre =:name ", nativeQuery = true)
    Editorial findByValueField(@Param("name")String name);
    
    Boolean existsByNombre(String nombre);
}
