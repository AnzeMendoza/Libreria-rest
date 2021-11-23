package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.repository.EditorialRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EditorialServiceImplTest {
    @Mock
    private EditorialRepository editorialRepository;
    @InjectMocks
    private  EditorialServiceImpl editorialService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown(){
    }

    @Test
    void changeStatuTest() throws ExceptionBBDD {
     when(editorialRepository.changeStatus(1,Boolean.TRUE)).thenReturn("OK");
     assertNotNull(editorialService.changeStatus(1,Boolean.TRUE));

    }
}