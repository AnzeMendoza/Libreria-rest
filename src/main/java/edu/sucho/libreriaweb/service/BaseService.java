package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;

import java.util.List;

public interface BaseService<E, I> {
    E save(E entity) throws ExceptionBBDD, ExceptionBadRequest;
    E update(I i, E entity) throws ExceptionBBDD, ExceptionBadRequest;
    Boolean delete(I i) throws ExceptionBBDD;
    E findById(I i) throws ExceptionBBDD;
    List<E> findAll() throws ExceptionBBDD;
  
//    Boolean deleteByIdSoft(I i, E entity) throws ExceptionBBDD;
}
