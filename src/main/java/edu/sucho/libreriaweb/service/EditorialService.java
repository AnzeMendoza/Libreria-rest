package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.Editorial;

import java.util.List;

public interface EditorialService extends BaseService<Editorial, Integer>{

    boolean deleteByIdSoft(int id) throws ExceptionBBDD;

    List<Editorial> findAllByAlta() throws ExceptionBBDD;

    Editorial saveEditorial(Editorial editorial) throws ExceptionBBDD, ExceptionBadRequest;

}
