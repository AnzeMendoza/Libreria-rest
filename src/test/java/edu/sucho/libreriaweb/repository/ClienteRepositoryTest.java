
package edu.sucho.libreriaweb.repository;



import java.util.List;
import edu.sucho.libreriaweb.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import edu.sucho.libreriaweb.model.entity.Cliente;
import edu.sucho.libreriaweb.util.Comparacion;
import edu.sucho.libreriaweb.util.Conexion;
import edu.sucho.libreriaweb.util.Util;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
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
    @Test
    void clienteRepositoryNotNullTest() {

        Assertions.assertNotNull(clienteRepository, "la referencia al  repositorio cliente es  nula");

    }
    @DisplayName("Cambio de Estado Cliente")
    @org.junit.jupiter.api.Test
    void changeStatusTest() {
        String esperado = "OK," + id;
        String actual = clienteRepository.changeStatus(id, true);
        Assertions.assertEquals(esperado, actual, "fallo el cambio de estado");
    }

    @DisplayName("Modificar Cliente")
    @org.junit.jupiter.api.Test
    void updateClienteTest() {
        String esperado = "OK," + id;
        String actual = modificarCliente(id);
         Assertions.assertEquals(esperado, actual, "Existe ese documento.");
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
       Assertions.assertTrue(comparacion.IsEqualsLists(esperado,actual), "los array no son iguales");
    }

    @DisplayName("Buscar coincidencias por campo nombre")
    @Test
    void findClienteForPatternName() throws SQLException {
        
////        Prueba usando mockito
//        Cliente cliente = new Cliente();
//        cliente.setAlta(Boolean.TRUE);
//        cliente.setId(1);
//        cliente.setNombre("san yo");
//        List<Cliente> esperado = new ArrayList<>();
//        esperado.add(cliente);
//        ClienteRepository clienteRepository = Mockito.mock(ClienteRepository.class);
//        when(clienteRepository.findClienteForPatternName("san")).thenReturn(esperado);

        List<Cliente> esperado = Util.getClientes(conexion, "SELECT * FROM autor WHERE nombre LIKE 'nom%';");

        List<Cliente> actual = clienteRepository.findClienteForPatternName("san");
        Assertions.assertEquals(esperado, actual, "no son los mismo clientes");
    }
}
