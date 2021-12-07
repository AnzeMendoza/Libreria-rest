
package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.entity.Cliente;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import edu.sucho.libreriaweb.repository.ClienteRepository;
import edu.sucho.libreriaweb.service.inter.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import edu.sucho.libreriaweb.model.entity.Cliente;
import edu.sucho.libreriaweb.model.entity.Editorial;
import edu.sucho.libreriaweb.util.Comparacion;
import edu.sucho.libreriaweb.util.Conexion;
import edu.sucho.libreriaweb.util.Util;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


public class ClienteRepositoryTest {
    
    @Autowired
    ClienteRepository clienteRepository;
    static int id;
    static Connection conexion;
    static Comparacion<Cliente>comparacion;

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

    @DisplayName("Validar Referencia No Nula ClienteRepository")
    @org.junit.jupiter.api.Test
    void clienteRepositoryNotNullTest() {
        assertNotNull(clienteRepository, "la referencia al  repositorio editorial es  nula");
    }

    @DisplayName("Cambio de Estado Cliente")
    @org.junit.jupiter.api.Test
    void changeStatusTest() {
        String esperado = "OK";
        String actual = clienteRepository.changeStatus(id, true);
        assertEquals(esperado, actual, "fallo el cambio de estado");
    }

    @DisplayName("Modificar Cliente")
    @org.junit.jupiter.api.Test
    void updateClienteTest() {
        String esperado = "OK," + id;
        String actual = modificarCliente(id);
        assertEquals(esperado, actual, "Existe ese documento.");
    }

    private String modificarCliente(int id) {
        String nombreCliente= "PruebaModificar";
        String apellidoCliente="PruebaModificar";
        String telefonoCliente="PruebaModificar";
        Long documentoCliente;
        String actual;
        do {
            documentoCliente = Long.parseLong(new Random().ints(9, 0, 9).boxed().map(value -> value.toString()).collect(Collectors.joining()));
     actual = clienteRepository.updateCliente(id, documentoCliente,nombreCliente,apellidoCliente,telefonoCliente );
        } while (actual == "Existe ese documento.");
        return actual;
    }

    @DisplayName("validar Clientes activos")
    @org.junit.jupiter.api.Test
    void findAllByAltaTest() throws SQLException {
        List<Cliente> esperado = Util.getClientes(conexion, "SELECT * FROM cliente WHERE cliente.alta = true");
        List<Cliente> actual = clienteRepository.findAllByAlta();
        assertTrue(comparacion.IsEqualsLists(esperado,actual), "los array no son iguales");
    }

//    @DisplayName("validar Clientes por documento ")
//    @org.junit.jupiter.api.Test
//    void findByValueField() throws SQLException {
//        Long documento =37806561L;
//        Cliente esperado = Util.getEditoriales(conexion, "SELECT * FROM editorial WHERE editorial.nombre = \"santillana\" ").get(0);
//        Cliente actual = clienteRepository.findByValueField(documento);
//        assertEquals(esperado, actual, "no son los mismo editoriales");
//    }

   
    
}
