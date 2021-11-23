package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.Editorial;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.*;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class EditorialRepositoryTest {

    @Autowired
    EditorialRepository editorialRepository;
    static int id;



    @BeforeAll
    public static void beforeAllTest() {
        id = 1;
        System.out.println("para todos los test, sirve para declarar variables que se usan en todos los test");
        System.out.println("@BeforeAll --> beforeAllTest()");
    }
    @AfterAll
    public static void afterAllTest(){
        System.out.println("para todos los test, sirve para liberar recursos");
        System.out.println("@afterAllTest() --> afterAllTest()");
    }
    @BeforeEach
    void setUp() {

        System.out.println("se ejecuta por cada Test");
        System.out.println("@BeforeEach --> setUp()");
    }
    @AfterEach
    void tearDown() {
        System.out.println("se ejecuta por cada Test");
        System.out.println("@AfterEach --> tearDown()");
    }

    @DisplayName("Validar Referencia No Nula EditorialRepository")
    @Test
    void editorialRepositoryNotNullTest() {
        assertNotNull(editorialRepository,"la referencia al  repositorio editorial es  nula");
    }
    @DisplayName("Cambio de Estado Editorial")
    @Test
    void changeStatusTest() {
        String esperado = "OK";
        String actual = editorialRepository.changeStatus(id,Boolean.TRUE);
        assertEquals(esperado, actual, "fallo el cambio de estado");
    }


    @DisplayName("Modificar Editorial")
    @Test
     void updateEditorialTest(){
        String esperado = "OK,"+id;
        String actual = modificarEditorial(id);
        assertEquals(esperado,actual,"ya existe  un editorial   con ese nombre");
     }
    private String modificarEditorial(int id) {
        String nombreEditorial;
        String actual;
        do{
            nombreEditorial = UUID.randomUUID().toString().replace("-", "").substring(0,9);
             actual = editorialRepository.updateEditorial(id,nombreEditorial);
        }while(actual =="ya existe  un editorial   con ese nombre");
        return actual;
    }

    @DisplayName("validar Editoriales activos")
     @Test
     void findAllByAltaTest() throws SQLException, ClassNotFoundException {
        List<Editorial> esperado = editorialRepository.findAllByAlta();
        List<Editorial> actual = editorialRepository.findAllByAlta();
        assertEquals(esperado, actual,"no son iguales los array");
     }

    @DisplayName("validar Editoriales por nombre ")
    @Test
    void findByValueField() throws SQLException, ClassNotFoundException {
        Editorial esperado = editorialRepository.findByValueField("nombre");
        Editorial actual = editorialRepository.findByValueField("nombre");
        assertEquals(esperado, actual,"no son los mismo editoriales");
    }



}