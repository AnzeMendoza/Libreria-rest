package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.Editorial;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.repository.EditorialRepository;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EditorialServiceImplTest {
    @InjectMocks
    EditorialServiceImpl editorialService;
    @Mock
    BaseRepository<Editorial, Integer> baseRepository  = Mockito.mock(EditorialRepository.class);

    // patrones para trabajar con mockito  en pruebas unitarias.
    //Arrage,Act,Assert
    //Given,When,Then

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() throws ExceptionBBDD {
        //Web
        List<Editorial> editoriales = new ArrayList<>();
        Editorial edi = new Editorial();
        edi.setId(100);
        edi.setNombre("dgfd");
        edi.setAlta(true);
        edi.setLibros(null);
        editoriales.add(edi);
        when(baseRepository.findAll()).thenReturn(editoriales);
        //then
        List<Editorial> actuales= editorialService.findAll();
        //Assert
        assertEquals(editoriales.size(),actuales.size());
    }
    @Test
    void findAllV2() throws ExceptionBBDD {
        //Given
        List<Editorial> editoriales = new ArrayList<>();
        Editorial edi = new Editorial();
        edi.setId(100);
        edi.setNombre("dgfd");
        edi.setAlta(true);
        edi.setLibros(null);
        editoriales.add(edi);
        given(baseRepository.findAll()).willReturn(editoriales);
        //when
        List<Editorial> actuales= editorialService.findAll();
        //result
        assertEquals(editoriales.size(),actuales.size());
    }


    //given
    @Spy
    List <String> spyList = new ArrayList<>();
    @Mock
    List<String> mockList = new ArrayList<>();

    @Test
    public void spyTest() {
        //when
        spyList.add("1");
        spyList.add("2");
        //then
        verify(spyList).add("1");
        verify(spyList).add("2");
        assertEquals(2,spyList.size());
    }

    @Test
    public void mockTest() {
        given(mockList.size()).willReturn(2);
        //when
        mockList.add("1");
        mockList.add("2");
        mockList.add("3");

        //then
        verify(mockList).add("1");
        verify(mockList).add("2");
        assertEquals(3,mockList.size());
        /* falla , porque   con mock se mockean todos los metodos
         y sin embargo cuando se usa un spy (espia) funciona como un mock , pero
          los metodos funcionan como los reales
         */
    }








}