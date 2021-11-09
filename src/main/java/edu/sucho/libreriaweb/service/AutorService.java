package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.Autor;

import java.util.List;

public interface AutorService extends BaseService<Autor, Integer>{

    boolean deleteByIdSoft(int id) throws ExceptionBBDD;

    List<Autor> findAllByAlta() throws ExceptionBBDD;
}
