package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.entity.Editorial;
import edu.sucho.libreriaweb.util.Comparacion;
import edu.sucho.libreriaweb.util.Conexion;
import edu.sucho.libreriaweb.util.Util;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EditorialRepositoryTest {

    @Autowired
    EditorialRepository editorialRepository;
    static int id;
    static Connection conexion;
    static Comparacion<Editorial>comparacion;

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

    @DisplayName("Validar Referencia No Nula EditorialRepository")
    @Test
    void editorialRepositoryNotNullTest() {
        assertNotNull(editorialRepository, "la referencia al  repositorio editorial es  nula");
    }

    @DisplayName("Cambio de Estado Editorial")
    @Test
    void changeStatusTest() {
        String esperado = "OK";
        String actual = editorialRepository.changeStatus(id, true);
        assertEquals(esperado, actual, "fallo el cambio de estado");
    }

    @DisplayName("Modificar Editorial")
    @Test
    void updateEditorialTest() {
        String esperado = "OK," + id;
        String actual = modificarEditorial(id);
        assertEquals(esperado, actual, "ya existe  un editorial   con ese nombre");
    }

    private String modificarEditorial(int id) {
        String nombreEditorial;
        String actual;
        do {
            nombreEditorial = UUID.randomUUID().toString().replace("-", "").substring(0, 9);
            actual = editorialRepository.updateEditorial(id, nombreEditorial);
        } while (actual == "ya existe  un editorial   con ese nombre");
        return actual;
    }

    @DisplayName("validar Editoriales activos")
    @Test
    void findAllByAltaTest() throws SQLException {
        List<Editorial> esperado = Util.getEditoriales(conexion, "SELECT * FROM editorial WHERE editorial.alta = true");
        List<Editorial> actual = editorialRepository.findAllByAlta();
        assertTrue(comparacion.IsEqualsLists(esperado,actual), "los array no son iguales");
    }

    @DisplayName("validar Editoriales por nombre ")
    @Test
    void findByValueField() throws SQLException {
        String nombre ="santillana";
        Editorial esperado = Util.getEditoriales(conexion, "SELECT * FROM editorial WHERE editorial.nombre = \"santillana\" ").get(0);
        Editorial actual = editorialRepository.findByValueField(nombre);
        assertEquals(esperado, actual, "no son los mismo editoriales");
    }


}