package edu.sucho.libreriaweb.service.inter;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.dto.LibroRequestDTO;
import edu.sucho.libreriaweb.model.entity.Libro;

import java.util.List;

public interface LibroService extends BaseService<Libro, Integer> {
    List<Libro> findAllByAlta() throws ExceptionBBDD;

    Libro findByIdAndAlta(int id) throws ExceptionBBDD;

    String getMessageStatus(String responseStatus, boolean status) throws ExceptionBBDD;

    String disableStatus(int id) throws ExceptionBBDD;

    String enableStatus(int id) throws ExceptionBBDD;

    Libro save(LibroRequestDTO libro) throws ExceptionBBDD, ExceptionBadRequest;

    Libro update(int id, LibroRequestDTO libro) throws ExceptionBBDD, ExceptionBadRequest;
}
