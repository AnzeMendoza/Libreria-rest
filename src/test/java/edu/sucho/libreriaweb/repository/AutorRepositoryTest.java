package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.entity.Autor;
import edu.sucho.libreriaweb.util.Conexion;
import edu.sucho.libreriaweb.util.Util;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AutorRepositoryTest {
    
    @Autowired
    AutorRepository autorRepository;
    static int id;
    static Connection conexion;


    
    @BeforeAll
    public static void beforeAllTest() {
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

    @DisplayName("Validar Referencia No Nula AutorRepository")
    @Test
    void autorRepositoryNotNullTest() {
        Assertions.assertNotNull(autorRepository, "la referencia al  repositorio editorial es  nula");
    }

    @DisplayName("Cambio de Estado Autor")
    @Test
    void changeStatusTest() {
        String esperado = "OK";
        String actual = autorRepository.changeStatusSp(id, true);
        Assertions.assertEquals(esperado, actual, "falló el cambio de estado");
    }

    @DisplayName("Modificar Autor")
    @Test
    void updateAutorTest() {
        String esperado = "OK," + id;
        String actual = modificarAutor(id);
        Assertions.assertEquals(esperado, actual, "ya existe  un autores   con ese nombre");
    }

    private String modificarAutor(int id) {
        String nombreAutor =UUID.randomUUID().toString().replace("-", "").substring(0, 9);
        String actual = autorRepository.updateSp(id, nombreAutor);
     
        return actual;
    }

    @DisplayName("validar Autores activos")
    @Test
    void findAllByAltaTest() throws SQLException {
        List<Autor> esperado = Util.getAutores(conexion, "SELECT * FROM autor WHERE autor.alta = true");
        List<Autor> actual = autorRepository.findAllByAlta();
        Assertions.assertEquals(esperado, actual, "los array no son iguales");
        
    }

    @DisplayName("validar Autor por nombre ")
    @Test
    void findByValueField() throws SQLException {
        String nombre ="nombre";
        Autor esperado = Util.getAutores(conexion, "SELECT * FROM autor WHERE autor.nombre = \""+nombre+"\" ").get(0);
        Autor actual = autorRepository.findByValueField(nombre);
        Assertions.assertEquals(esperado, actual, "no son los mismo autores");
    }

    @DisplayName("Buscar coincidencias por campo nombre")
    @Test
    void findAutorForPatternName() throws SQLException {
        //Prueba usando mockito

//        Autor autor = new Autor();
//        autor.setAlta(Boolean.TRUE);
//        autor.setId(1);
//        autor.setNombre("san yo");
//        List<Autor> esperado = new ArrayList<>();
//        esperado.add(autor);
//        AutorRepository aRepository = Mockito.mock(AutorRepository.class);
//        when(aRepository.findAutorForPatternName("san")).thenReturn(esperado);

        List<Autor> esperado = Util.getAutores(conexion, "SELECT * FROM autor WHERE nombre LIKE 'nom%';");
        List<Autor> actual = autorRepository.findAutorForPatternName("nom");
        Assertions.assertEquals(esperado, actual, "no son los mismo autores");
    }
    
}
