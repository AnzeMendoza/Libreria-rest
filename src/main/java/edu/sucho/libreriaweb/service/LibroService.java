package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.Libro;

import java.util.List;

public interface LibroService extends BaseService<Libro, Integer> {
    List<Libro> findAllByAlta() throws ExceptionBBDD;

    Libro findByIdAndAlta(int id) throws ExceptionBBDD;

//    List<Libro> findByTitulo(String titulo) throws ExceptionBBDD;

//    boolean disableById(int id) throws ExceptionBBDD;

//    boolean enableById(int id) throws ExceptionBBDD;

//    List<Libro> findAllByAltaAndInStock() throws ExceptionBBDD;

//    Libro devolverLibro(int id) throws ExceptionBBDD;

//    Libro prestarLibro(int id) throws ExceptionBBDD;

    String getMessageStatus(String responseStatus, boolean status) throws ExceptionBBDD;

    String disableStatus(int id) throws ExceptionBBDD;

    String enableStatus(int id) throws ExceptionBBDD;
}
