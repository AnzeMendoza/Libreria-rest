package edu.sucho.libreriaweb.repository;

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
    String changeStatusSp(@Param("id") int id, @Param("alta") Boolean alta);

    @Query(value = "CALL lsp_modificar_libro(:id,:titulo,:isbn,:anio,"
            + ":ejemplares,:ejemplaresPrestados,:ejemplaresRestantes,"
            + ":autor,:editorial);", nativeQuery = true)
    String updateSp(@Param("id") Integer id, @Param("titulo") String titulo,
                    @Param("isbn") Long isbn, @Param("anio") Integer anio,
                    @Param("ejemplares") Integer ejemplares,
                    @Param("ejemplaresPrestados") Integer ejemplaresPrestados,
                    @Param("ejemplaresRestantes") Integer ejemplaresRestantes,
                    @Param("autor") Integer autorId, @Param("editorial") Integer editorialId);


    @Query( value = "CALL lsp_crear_libro(:titulo, :isbn, :anio, :ejemplares, :ejemplaresPrestados, :ejemplaresRestantes, :alta, :fk_autor, :fk_editorial)",
            nativeQuery = true)
    String createSp(@Param("alta") boolean alta,
                    @Param("anio") int anio,
                    @Param("ejemplares") int ejemplares,
                    @Param("ejemplaresPrestados") int ejemplaresPrestados,
                    @Param("ejemplaresRestantes") int ejemplaresRestantes,
                    @Param("isbn") Long isbn,
                    @Param("titulo") String titulo,
                    @Param("fk_autor") int fk_autor,
                    @Param("fk_editorial") int fk_editorial);


}
