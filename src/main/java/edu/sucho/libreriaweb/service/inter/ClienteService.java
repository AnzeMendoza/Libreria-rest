package edu.sucho.libreriaweb.service.inter;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.dto.ClienteRequestDTO;
import edu.sucho.libreriaweb.model.entity.Cliente;

import java.util.List;

public interface ClienteService extends BaseService<Cliente, Integer> {
    List<Cliente> findAllByAlta() throws ExceptionBBDD;

    Cliente save(ClienteRequestDTO cliente) throws ExceptionBBDD;

    Cliente update(Integer id, ClienteRequestDTO cliente) throws ExceptionBBDD;

    String changeStatus(int id, Boolean estado) throws ExceptionBBDD;

    Cliente findByUsername(String username);

    Integer findIdByDocumento(long documento) throws ExceptionBBDD;
}
