package edu.sucho.libreriaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface BaseRepository<E, I> extends JpaRepository<E, I> {
    @Query(value = "ALTER TABLE :table AUTO_INCREMENT = 1", nativeQuery = true)
    void resetId(@Param("table") String table);
}
