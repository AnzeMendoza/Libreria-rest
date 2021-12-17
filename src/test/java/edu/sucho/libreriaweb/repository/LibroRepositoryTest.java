package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.entity.Libro;
import edu.sucho.libreriaweb.util.Conexion;
import edu.sucho.libreriaweb.util.Util;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class LibroRepositoryTest {

    @Autowired
    private LibroRepository libroRepository;

    static int id;

    static Connection conexion;



    @BeforeAll
    public static void beforeAllTest(){
        id = 1;
        System.out.println("para todos los test, sirve para declarar variables que se usan en todos los test");
        System.out.println("@BeforeAll --> beforeAllTest()");
    }

    @AfterAll
    public static void afterAllTest() {
        System.out.println("para todos los test, sirve para liberar recursos");
        System.out.println("@afterAllTest() --> afterAllTest()");
    }

    @BeforeEach
    void setUp() {
        conexion = Conexion.conect();

        System.out.println("se ejecuta por cada Test");
        System.out.println("@BeforeEach --> setUp()");
    }

    @AfterEach
    void tearDown() {
        Conexion.disconect(conexion);
        System.out.println("se ejecuta por cada Test");
        System.out.println("@AfterEach --> tearDown()");
    }

    @DisplayName("Validar referencia no nula LibroRepository")
    @Test
    void libroRepositoryNotNullTest(){
        Assertions.assertNotNull(libroRepository, "la referencia al  repositorio libro es  nula");
    }

    @DisplayName("validar Libros activos")
    @Test
    void findAllByAltaTest() throws SQLException {
        List<Libro> esperado = Util.getLibro(conexion, "SELECT * FROM libro WHERE libro.alta = true");
        List<Libro> actual = libroRepository.findAllByAlta();
        Assertions.assertEquals(esperado,actual, "los arrays no son iguales");
    }

    @DisplayName("validar Libros activos y con stock dsiponible")
    @Test
    void findAllByAltaAndInStock() throws SQLException {
        List<Libro> esperado = Util.getLibro(conexion, "SELECT * FROM libro WHERE libro.alta = true AND libro.ejemplares_restantes > 0");
        List<Libro> actual = libroRepository.findAllByAltaAndInStock();
        Assertions.assertEquals(esperado,actual, "los arrays no son iguales");
    }

    @DisplayName("Encuentra el libro con el titulo buscado")
    @Test
    void findByTitulo() throws SQLException{
        List<Libro> esperados = Util.getLibro(conexion, "SELECT * FROM libro WHERE libro.titulo = 'Titulo uno';");
        List<Libro> actual = libroRepository.findByTitulo("Titulo uno");
        Assertions.assertEquals(esperados.size(),actual.size(),"Los arrays no son iguales");
    }

    // Camino Feliz
    @DisplayName("Encuentra el libro con el titulo buscado con Mockito")
    @Test
    void findByTituloV_2() {
        LibroRepository libroRepository = Mockito.mock(LibroRepository.class);
        when(libroRepository.findByTitulo("Titulo uno")).thenReturn(Arrays.asList(new Libro()));
        List<Libro> actual = libroRepository.findByTitulo("Titulo uno");
        Assertions.assertEquals(1,actual.size(),"Los arrays no son iguales");
    }

    @DisplayName("Modificar estado de un Libro")
    @Order(2)
    @Test
    void changeStatusSp() {
        String esperado = "OK";
        String actual = libroRepository.changeStatusSp(id,false);
        Assertions.assertEquals(esperado, actual, "La actualización no fue correcta");
    }

    @DisplayName("Modificar Libro")
    @Test
    void updateTest() {
        String esperado = "OK,"+ id ;
        String actual = libroRepository.updateSp(id,"1",12345678L,2020,20,10,10,1,1);
        Assertions.assertEquals(esperado, actual, "La actualización no fue correcta");
    }

    // CON MOCKS
    @DisplayName("Modificar Libro con mocks")
    @Test
    void updateTestV_2() {
        LibroRepository libroRepository = Mockito.mock(LibroRepository.class);
        when(libroRepository.updateSp(id,"1",12345678L,2020,20,10,10,200,1)).thenReturn("ESTA MAL");
        String esperado = "OK,"+ id ;
        String actual = libroRepository.updateSp(id,"1",12345678L,2020,20,10,10,200,1);
        Assertions.assertEquals(esperado, actual, "La actualización no fue correcta");
    }

    @DisplayName("Buscar coincidencias por campo nombre")
    @Test
    void findTituloForPattern() throws SQLException {
/*        Libro libro = new Libro();
        libro.setId(1);
        libro.setTitulo("física");
        libro.setIsbn(12344231L);
        libro.setAnio(2005);
        libro.setEjemplares(180);
        libro.setEjemplaresPrestados(20);
        libro.setEjemplaresRestantes(160);
        libro.setAlta(Boolean.TRUE);

        System.out.println(libro);

        List<Libro> esperado = new ArrayList<>();
        esperado.add(libro);

        LibroRepository libroMockRepository = Mockito.mock(LibroRepository.class);
        when(libroMockRepository.findTituloForPattern("fís")).thenReturn(esperado);

//        List<Autor> esperado = Util.getAutores(conexion, "SELECT * FROM autor WHERE nombre LIKE 'nom%';");
        List<Libro> actual = libroMockRepository.findTituloForPattern("fís");*/
        List<Libro> librosQuery = Util.getLibro(conexion, "SELECT * FROM libro WHERE titulo LIKE 'fís%'");
        List<Libro> librosStoreProcedure = libroRepository.findTituloForPattern("fís");
        Assertions.assertEquals(librosQuery, librosStoreProcedure, "no son los mismo libros");
    }
}