package edu.sucho.libreriaweb.repository;

import edu.sucho.libreriaweb.model.entity.Editorial;
import edu.sucho.libreriaweb.model.mapper.ModelMapperDTO;
import edu.sucho.libreriaweb.util.Comparacion;
import edu.sucho.libreriaweb.util.Conexion;
import edu.sucho.libreriaweb.util.Util;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
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

    @DisplayName("Validar Referencia No Nula EditorialRepository")
    @Test
    @Order(1)
    void editorialRepositoryNotNullTest() {
        Assertions.assertNotNull(editorialRepository, "no funciona el autowired");
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



   @DisplayName("validar Editoriales por nombre ")
    @Test
    void findByValueField() throws SQLException {
        String nombre ="santillana";
        Editorial esperado = Util.getEditoriales(conexion, "SELECT * FROM editorial WHERE editorial.nombre = \"santillana\" ").get(0);
        Editorial actual = editorialRepository.findByValueField(nombre);
        Assertions.assertEquals(esperado, actual, "no son los mismo editoriales");
    }

    @DisplayName("Buscar coincidencias en el campo nombre")
    @Test
    @Order(2)
    void findByValueName() throws SQLException {
      /*  Editorial editorial = new Editorial();
        editorial.setAlta(Boolean.TRUE);
        editorial.setId(1);
        editorial.setNombre("kape");
        List<Editorial> esperado = new ArrayList();
        esperado.add(editorial);*/
      
        String patron="c";
        List<Editorial> esperado =
                Util.getEditoriales(conexion, "SELECT * FROM editorial WHERE nombre LIKE "+ patron +"%");
        
        
       // EditorialRepository editorialRepo = Mockito.mock(EditorialRepository.class);

       // when(editorialRepo.findEditorialForPatternName("kape")).thenReturn(esperado);

        List<Editorial> actual = editorialRepository
                .findEditorialForPatternName(patron);
        Assertions.assertEquals(esperado, actual, "Las editoriales no son iguales");
    }
}
