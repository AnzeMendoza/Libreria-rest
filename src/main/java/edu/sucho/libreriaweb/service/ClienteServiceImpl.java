package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.Autor;
import edu.sucho.libreriaweb.model.Cliente;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Integer> implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteServiceImpl(BaseRepository<Cliente, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public boolean deleteByIdSoft(int id) throws ExceptionBBDD {
        try {
            Optional<Cliente> clienteOptional = clienteRepository.findById(id);

            if(clienteOptional.isPresent()){
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
}
