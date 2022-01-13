package edu.sucho.libreriaweb.service.impl;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;

import edu.sucho.libreriaweb.model.dto.ClienteRequestDTO;
import edu.sucho.libreriaweb.model.entity.Cliente;
import edu.sucho.libreriaweb.model.mapper.UserDetailsMapper;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.repository.ClienteRepository;
import edu.sucho.libreriaweb.service.inter.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Integer> implements ClienteService {

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public ClienteServiceImpl(BaseRepository<Cliente, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Cliente retrievedUser = clienteRepository.findByUsername(username);
        if (retrievedUser == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return userDetailsMapper.build(retrievedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAllByAlta() throws ExceptionBBDD {
        try {
            Optional<List<Cliente>> clientesOptional = Optional.ofNullable(clienteRepository.findAllByAlta());
            return clientesOptional.get();
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    @Override
    public Cliente save(ClienteRequestDTO cliente) throws ExceptionBBDD {
        String passwordEncode = encoder.encode(cliente.getPassword());
        return retornarCliente(clienteRepository
                .saveCliente(cliente.getDocumento(), cliente.getNombre(), cliente.getApellido(), cliente.getTelefono(), cliente.getUsername(),passwordEncode,cliente.getRoleId()));
    }

    @Override
    public Cliente update(Integer id, Cliente cliente) throws ExceptionBBDD {
        return retornarCliente(clienteRepository
                .updateCliente(cliente.getId(), cliente.getDocumento(), cliente.getNombre(), cliente.getApellido(), cliente.getTelefono()));
    }

    @Override
    public String changeStatus(int id, Boolean estado) throws ExceptionBBDD {
        return retornarMensaje(clienteRepository.changeStatus(id, estado), estado);
    }

    private String retornarMensaje(String resultado, Boolean estado) throws ExceptionBBDD {
        if (resultado.contains("OK")) {
            return (estado) ? "Cliente Activado" : "Cliente Desactivado";
        }
        throw new ExceptionBBDD(resultado);
    }

    private Cliente retornarCliente(String resultado) throws ExceptionBBDD {
        if (resultado.contains("OK")) {
            int id = Integer.parseInt(resultado.split(",")[1]);
            return clienteRepository.findById(id).get();
        }
        throw new ExceptionBBDD(resultado);
    }
}
