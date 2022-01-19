package edu.sucho.libreriaweb.service.inter;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;

import java.util.List;

public interface BaseService<E, I> {
    E save(E entity) throws ExceptionBBDD, ExceptionBadRequest;

    E update(I i, E entity) throws ExceptionBBDD, ExceptionBadRequest;

    Boolean delete(I i) throws ExceptionBBDD;

    E findById(I i) throws ExceptionBBDD, ExceptionBadRequest;

    List<E> findAll() throws ExceptionBBDD;
}