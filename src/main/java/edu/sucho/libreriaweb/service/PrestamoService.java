package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.Prestamo;

import java.util.List;

public interface PrestamoService extends BaseService<Prestamo, Integer>{

    List<Prestamo> findAllByAlta() throws ExceptionBBDD;
}
