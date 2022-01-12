package edu.sucho.libreriaweb.service.inter;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.dto.ClienteRequestDTO;
import edu.sucho.libreriaweb.model.entity.Cliente;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ClienteService extends BaseService<Cliente, Integer>, UserDetailsService {
    List<Cliente> findAllByAlta() throws ExceptionBBDD;

    Cliente save(ClienteRequestDTO cliente) throws ExceptionBBDD;

    Cliente update(Integer id, Cliente cliente) throws ExceptionBBDD;

    String changeStatus(int id, Boolean estado) throws ExceptionBBDD;
}
