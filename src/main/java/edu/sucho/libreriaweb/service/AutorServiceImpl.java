package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.Autor;
import edu.sucho.libreriaweb.repository.AutorRepository;
import edu.sucho.libreriaweb.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl extends BaseServiceImpl<Autor, Integer> implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public AutorServiceImpl(BaseRepository<Autor, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public boolean deleteByIdSoft(int id) throws ExceptionBBDD {
        try {
            Optional<Autor> libroOptional = autorRepository.findById(id);

            if(libroOptional.isPresent()){
                Autor autor = libroOptional.get();
                autor.setAlta(!autor.getAlta());
                autorRepository.save(autor);
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
    public List<Autor> findAllByAlta() throws ExceptionBBDD {
        try {
            Optional<List<Autor>> autoresOptional = Optional.ofNullable(autorRepository.findAllByAlta());
            return autoresOptional.get();
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    @Override
    public String enableStatus(int id) throws ExceptionBBDD {
        return getMessageStatus(autorRepository.changeStatus(id, Boolean.TRUE), Boolean.TRUE);
    }

    @Override
    public String disableStatus(int id) throws ExceptionBBDD {
        return getMessageStatus(autorRepository.changeStatus(id, Boolean.FALSE), Boolean.FALSE);
    }

    @Override
    public String getMessageStatus(String responseStatus, boolean status) throws ExceptionBBDD {
        if(!responseStatus.contains("OK")){
            throw new ExceptionBBDD(responseStatus);
        }
        return status? "Autor Activado" : "Autor Desactivado";
    }
    
}
