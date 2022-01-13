package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role,Integer>{

    @Query(value = "SELECT * FROM role WHERE role.name = :name", nativeQuery = true)
    Role findByName(@Param("name") String name);
}
