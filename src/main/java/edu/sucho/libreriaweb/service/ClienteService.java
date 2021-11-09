package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.Cliente;

import java.util.List;

public interface ClienteService extends BaseService<Cliente, Integer> {

    boolean deleteByIdSoft(int id) throws ExceptionBBDD;

    List<Cliente> findAllByAlta() throws ExceptionBBDD;
}
