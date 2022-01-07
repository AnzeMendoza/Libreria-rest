package edu.sucho.libreriaweb.service.impl;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;

import edu.sucho.libreriaweb.model.entity.Cliente;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.repository.ClienteRepository;
import edu.sucho.libreriaweb.security.EncryptPassword;
import edu.sucho.libreriaweb.service.inter.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Integer> implements ClienteService  {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EncryptPassword encryptPassword;

    public ClienteServiceImpl(BaseRepository<Cliente, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public boolean deleteByIdSoft(int id) throws ExceptionBBDD {
        try {
            Optional<Cliente> clienteOptional = clienteRepository.findById(id);

            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                cliente.setAlta(!cliente.getAlta());
                clienteRepository.save(cliente);
            } else {
                throw new ExceptionBBDD("deleteByIdSoft");
            }
            return true;
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
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
    public Cliente save(Cliente cliente) throws ExceptionBBDD {
        String userPassswordCrypt = encryptPassword.encriptarCadena(cliente.getUserPassword());

        return retornarCliente(clienteRepository
                .saveCliente(cliente.getDocumento(),
                        cliente.getNombre(),
                        cliente.getApellido(),
                        cliente.getTelefono(),
                        cliente.getUsername(),
                        userPassswordCrypt,
                        cliente.getRol().getId()));
    }

    @Override
    public Cliente update(Integer id, Cliente cliente) throws ExceptionBBDD {
        return retornarCliente(clienteRepository
                .updateCliente(cliente.getId(), cliente.getDocumento(), cliente.getNombre(), cliente.getApellido(), cliente.getTelefono()));
    }

    @Override
    public Cliente findByUsername(String username) throws ExceptionBBDD {
        Cliente clienteEncontrado = clienteRepository.findByUsername(username).get();
        if(clienteEncontrado==null){
            throw new ExceptionBBDD("No se encontro el username");
        }
        return clienteEncontrado;
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
