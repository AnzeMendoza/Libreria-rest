package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.Libro;

import java.util.List;

public interface LibroService extends BaseService<Libro, Integer>{

    List<Libro> findAllByAlta() throws ExceptionBBDD;

    Libro findByIdAndAlta(int id) throws ExceptionBBDD;

    List<Libro> findByTitulo(String titulo) throws ExceptionBBDD;

    boolean deleteByIdSoft(int id) throws ExceptionBBDD;

    List<Libro> findAllByAltaAndInStock() throws ExceptionBBDD;

    Libro addOneLibro(int id) throws ExceptionBBDD;

    Libro substractOneLibro(int id) throws ExceptionBBDD;

}
