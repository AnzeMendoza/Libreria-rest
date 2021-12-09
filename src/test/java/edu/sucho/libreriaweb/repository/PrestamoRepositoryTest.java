package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.entity.Editorial;
import edu.sucho.libreriaweb.model.entity.Prestamo;
import edu.sucho.libreriaweb.util.Comparacion;
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
public class PrestamoRepositoryTest {

    @Autowired
    private PrestamoRepository prestamoRepository;

    static int id;

    static Connection conexion;

    static Comparacion<Prestamo> comparacion;

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

    @DisplayName("Validar referencia no nula PrestamoRepository")
    @Test
    void prestamoRepositoryNotNullTest(){
        Assertions.assertNotNull(prestamoRepository, "la referencia al  repositorio editorial es  nula");
    }


    @DisplayName("Modificar Prestamo")
    @Test
    void updateEstadoTest() {
        String esperado = "OK";
        String actual = modificarPrestamo(id);
        Assertions.assertEquals(esperado, actual, "La actualización no fue correcta");
    }

    private String modificarPrestamo(int id) {
        return prestamoRepository.changeStatusSp(id, true);
    }

    @DisplayName("validar Prestamos activos")
    @Test
    void findAllByAltaTest() throws SQLException {
        List<Prestamo> esperado = Util.getPrestamo(conexion, "SELECT * FROM prestamo WHERE prestamo.alta = true");
        List<Prestamo> actual = prestamoRepository.findAllByAlta();
        Assertions.assertTrue(comparacion.IsEqualsLists(esperado,actual), "los array no son iguales");
    }
}
