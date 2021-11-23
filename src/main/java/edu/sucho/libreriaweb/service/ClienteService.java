package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.Cliente;

import java.util.List;

public interface ClienteService extends BaseService<Cliente, Integer> {

    boolean deleteByIdSoft(int id) throws ExceptionBBDD;

    List<Cliente> findAllByAlta() throws ExceptionBBDD;
     Cliente save(Cliente cliente) throws ExceptionBBDD;
    Cliente update (Integer id,Cliente cliente) throws ExceptionBBDD;
    String changeStatus(int id,Boolean estado)throws ExceptionBBDD;
}
