package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.Autor;
import edu.sucho.libreriaweb.model.Editorial;
import edu.sucho.libreriaweb.model.Libro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends BaseRepository<Libro, Integer> {

    @Query(value = "SELECT * FROM libro WHERE libro.alta = true", nativeQuery = true)
    List<Libro> findAllByAlta();

    @Query(value = "SELECT * FROM libro WHERE libro.alta = true AND libro.ejemplares_restantes > 0",
            nativeQuery = true)
    List<Libro> findAllByAltaAndInStock();

    @Query(value = "SELECT * FROM libro WHERE libro.id = :id AND libro.alta = true ", nativeQuery = true)
    Libro findByIdAndAlta(@Param("id") int id);

    @Query(value = "SELECT * FROM libro WHERE libro.titulo LIKE %:titulo%", nativeQuery = true)
    List<Libro> findByTitulo(@Param("titulo") String titulo);
    
    @Query(value = "CALL lsp_cambiar_estado_libro(:id,:alta);", nativeQuery = true)
    String changeStatus(@Param("id") int id, @Param("alta") Boolean alta);
    
    @Query(value = "CALL lsp_modificar_libro(:id,:titulo,:isbn,:anio,"
            + ":ejemplares,:ejemplaresPrestados,:ejemplaresRestantes,"
            + ":autor,:editorial);", nativeQuery = true)
    String updateEditorial(@Param("id") Integer id, @Param("titulo") String titulo,
            @Param("isbn") Long isbn,@Param("anio") Integer anio,
            @Param("ejemplares") Integer ejemplares,
            @Param("ejemplaresPrestados") Integer ejemplaresPrestados,
            @Param("ejemplaresRestantes") Integer ejemplaresRestantes,
            @Param("autor") Autor autor,@Param("editorial") Editorial editorial);
}
