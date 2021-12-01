package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.Editorial;
import edu.sucho.libreriaweb.service.EditorialService;
import edu.sucho.libreriaweb.service.EditorialServiceImpl;
import edu.sucho.libreriaweb.util.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.sql.*;
import java.util.List;
import java.util.NoSuchElementException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EditorialRepositoryTest {

    @Autowired
    EditorialRepository editorialRepository;
    static int id;
    static Connection conexion;
    static Comparacion<Editorial> comparacion;

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
        comparacion = new Comparacion<>();
        System.out.println("se ejecuta por cada Test");
        System.out.println("@BeforeEach --> setUp()");
    }

    @AfterEach
    void tearDown() {
        Conexion.disconect(conexion);
        comparacion = null;
        System.out.println("se ejecuta por cada Test");
        System.out.println("@AfterEach --> tearDown()");
    }

    @Test
    @DisplayName("Referencia No Nula EditorialRepository")
    @Order(1)
    void editorialRepositoryNotNullTest() {
        assertNotNull(editorialRepository, "la referencia al  repositorio editorial es  nula");
    }

    @Test
    @DisplayName("Obtener Editorial")
    @Order(2)
    void getEditorialTest() {
        Editorial editorial = editorialRepository.findById(id).get();
        assertNotNull(editorial, "no existe el editorial con este id");
    }


    @Test
    @DisplayName("Editorial no esta en Base de Datos")
    @Order(3)
    void notPersistedEditorialTest() {
        String esperado = "No value present";
        NoSuchElementException thrown = assertThrows(NoSuchElementException.class, () -> {
            Editorial editorial = editorialRepository.findById(Integer.MAX_VALUE).get();
        });
        Assertions.assertEquals(esperado, thrown.getMessage(), "El editorial se encuentra en la base de datos");
    }

    @Test
    @DisplayName("Cambiar Estado del Editorial a Activado ")
    @Order(4)
    void StatusActiveEditorialTest() {
        String esperado = "OK";
        String actual = editorialRepository.changeStatus(id, true);
        assertEquals(esperado, actual, "fallo el cambio de estado");
    }

    @Test
    @DisplayName("Cambiar Estado del Editorial a Desactivado")
    @Order(5)
    void StatusDisabledEditorialTest() {
        String esperado = "OK";
        String actual = editorialRepository.changeStatus(id, false);
        assertEquals(esperado, actual, "fallo el cambio de estado");
    }

    @DisplayName("Crear Editorial")
    @Test
    @Order(6)
    void createEditorialTest() {
        String esperado = "OK," + (Util.getIdMax(conexion, "SELECT max(id) FROM editorial") + 1);
        String nombre = generarNombreUnico();
        String actual = editorialRepository.saveEditorial(nombre);
        assertEquals(esperado, actual, "ya existe  un editorial   con ese nombre");
    }

    @DisplayName("Modificar Editorial")
    @Test
    @Order(7)
    void updateEditorialTest() {
        String esperado = "OK," + id;
        String nombre = generarNombreUnico();
        String actual = editorialRepository.updateEditorial(id, nombre);
        assertEquals(esperado, actual, "ya existe  un editorial   con ese nombre");
    }

    @DisplayName("Editoriales activos")
    @Test
    @Order(8)
    void findAllByAltaTest() throws SQLException {
        List<Editorial> esperado = Util.getEditoriales(conexion, "SELECT * FROM editorial WHERE editorial.alta = true");
        List<Editorial> actual = editorialRepository.findAllByAlta();
        assertTrue(comparacion.IsEqualsLists(esperado, actual), "los array no son iguales");
    }

    @DisplayName("Editorial por nombre")
    @Test
    @Order(9)
    void findByValueField() throws SQLException {
        String nombre = "santillana";
        Editorial esperado = Util.getEditoriales(conexion, "SELECT * FROM editorial WHERE editorial.nombre = \"santillana\" ").get(0);
        Editorial actual = editorialRepository.findByValueField(nombre);
        assertEquals(esperado, actual, "no son los mismo editoriales");
    }


    @DisplayName("Primer  Test Mockito")
    @Test
    @Order(9)
    void mockitotest() {
        editorialRepository = Mockito.mock(EditorialRepository.class);
        when(editorialRepository.changeStatus(100,true)).thenReturn("Hola Mundo");
        String actual =editorialRepository.changeStatus(100,true);
        assertEquals(actual, actual);
    }






    private String generarNombreUnico() {
        String nombre = "";
        do {
            nombre = Util.generarString();
        } while (existEditorial(nombre));
        return nombre;
    }

    private boolean existEditorial(String nombre) {
        return editorialRepository.findByValueField(nombre) != null;
    }


}