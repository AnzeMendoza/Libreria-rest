package edu.sucho.libreriaweb.service.inter;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<E, I> {
    E save(E entity) throws ExceptionBBDD, ExceptionBadRequest;

    E update(I i, E entity) throws ExceptionBBDD, ExceptionBadRequest;

    Boolean delete(I i) throws ExceptionBBDD;

    E findById(I i) throws ExceptionBBDD, ExceptionBadRequest;

    List<E> findAll() throws ExceptionBBDD;

    Page<E> findAll(Pageable pageable) throws ExceptionBBDD;
}