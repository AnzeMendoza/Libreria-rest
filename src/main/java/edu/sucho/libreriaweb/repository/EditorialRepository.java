package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.Editorial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EditorialRepository extends BaseRepository<Editorial, Integer> {
    @Query(value = "SELECT * FROM editorial WHERE editorial.alta = true", nativeQuery = true)
    List<Editorial> findAllByAlta();
}
