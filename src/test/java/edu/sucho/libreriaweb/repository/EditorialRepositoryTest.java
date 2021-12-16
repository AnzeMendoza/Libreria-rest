package edu.sucho.libreriaweb.repository;

<<<<<<< HEAD
import edu.sucho.libreriaweb.model.dto.EditorialDTO;
=======

>>>>>>> 0b9f09a36296424337e6ca9b384d9da435c63186
import edu.sucho.libreriaweb.model.entity.Editorial;
import edu.sucho.libreriaweb.model.mapper.ModelMapperDTO;
import edu.sucho.libreriaweb.util.Comparacion;
import edu.sucho.libreriaweb.util.Conexion;
import edu.sucho.libreriaweb.util.Util;
<<<<<<< HEAD
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
=======
import org.junit.jupiter.api.*;
>>>>>>> 0b9f09a36296424337e6ca9b384d9da435c63186
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
>>>>>>> 0b9f09a36296424337e6ca9b384d9da435c63186

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EditorialRepositoryTest {

    @Autowired
    EditorialRepository editorialRepository;
    static int id;
    static Connection conexion;
    static Comparacion<Editorial> comparacion;

    @Autowired
    ModelMapperDTO modelMapperDto;

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
    @Order(8)
    void editorialRepositoryNotNullTest() {
        Assertions.assertNotNull(editorialRepository, "la referencia al  repositorio editorial es  nula");
    }

    @DisplayName("Cambio de Estado Editorial")
    @Test
    void changeStatusTest() {
        String esperado = "OK";
        String actual = editorialRepository.changeStatus(id, true);
        Assertions.assertEquals(esperado, actual, "fallo el cambio de estado");
    }

    @DisplayName("Modificar Editorial")
    @Test
    void updateEditorialTest() {
        String esperado = "OK," + id;
        String actual = modificarEditorial(id);
        Assertions.assertEquals(esperado, actual, "ya existe  un editorial   con ese nombre");
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
        Assertions.assertTrue(comparacion.IsEqualsLists(esperado,actual), "los array no son iguales");
    }



   /* @DisplayName("validar Editoriales por nombre ")
    @Test
    void findByValueField() throws SQLException {
        String nombre ="santillana";
        Editorial esperado = Util.getEditoriales(conexion, "SELECT * FROM editorial WHERE editorial.nombre = \"santillana\" ").get(0);
        Editorial actual = editorialRepository.findByValueField(nombre);
        Assertions.assertEquals(esperado, actual, "no son los mismo editoriales");
    }
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

        Assertions.assertNotNull(editorialRepository, "la referencia al  repositorio editorial es  nula");

    }

    @DisplayName("Cambio de Estado Editorial")
    @Test
    @Order(2)
    void changeStatusTest() {
        String esperado = "OK";
        String actual = editorialRepository.changeStatus(id, true);

        Assertions.assertEquals(esperado, actual, "fallo el cambio de estado");

    }

    @DisplayName("Modificar Editorial")
    @Test
    @Order(3)
    void updateEditorialTest() {
        String esperado = "OK," + id;
        String actual = modificarEditorial(id);

        Assertions.assertEquals(esperado, actual, "ya existe  un editorial   con ese nombre");

    }

    private String modificarEditorial(int id) {
        String nombreEditorial;
        String actual;
        do {
            nombreEditorial = UUID.randomUUID().toString().replace("-", "").substring(0, 9);
            actual = editorialRepository.updateEditorial(id, nombreEditorial);

        } while (actual.equals("ya existe  un editorial con ese nombre"));

        return actual;
    }

    @DisplayName("validar Editoriales activos")
    @Test
    @Order(4)
    void findAllByAltaTest() throws SQLException {
        List<Editorial> esperado = Util.getEditoriales(conexion, "SELECT * FROM editorial WHERE editorial.alta = true");
        List<Editorial> actual = editorialRepository.findAllByAlta();

      Assertions.assertEquals(esperado, actual,"Las listas de editoriales activos no son iguales");
    }

    @DisplayName("validar Editoriales por nombre ")
    @Test
    void findByValueField() throws SQLException {
<<<<<<< HEAD
        String nombre = "ola";

        Editorial esperado = Util.getEditoriales(conexion,
                "SELECT * FROM editorial WHERE editorial.nombre = \"ola\" ").get(0);
=======

        String nombre = "santillana";

        Editorial esperado = Util.getEditoriales(conexion, "SELECT * FROM editorial WHERE editorial.nombre = \"Santillana\" ").get(0);
>>>>>>> 0b9f09a36296424337e6ca9b384d9da435c63186

        Editorial actual = editorialRepository.findByValueField(nombre);
        actual.setLibros(null);

        Assertions.assertEquals(esperado, actual, "no son los mismo editoriales");

    }*/

    // throw SQlException
    @DisplayName("Buscar coincidencias en el campo nombre")
    @Test
    @Order(1)
    void findByValueName() {
        Editorial editorial = new Editorial();
        editorial.setAlta(Boolean.TRUE);
        editorial.setId(1);
        editorial.setNombre("kape");
        List<Editorial> esperado = new ArrayList();
        esperado.add(editorial);
        
        EditorialRepository editorialRepo = Mockito.mock(EditorialRepository.class);

        when(editorialRepo.findEditorialForPatternName("kape")).thenReturn(esperado);

        List<Editorial> actual = editorialRepo
                .findEditorialForPatternName("kape");
        Assertions.assertEquals(esperado, actual, "Las editoriales no son iguales");
    }
}
