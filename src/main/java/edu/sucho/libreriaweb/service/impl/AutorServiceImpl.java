package edu.sucho.libreriaweb.service.impl;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.entity.Autor;
import edu.sucho.libreriaweb.repository.AutorRepository;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.service.inter.AutorService;
import edu.sucho.libreriaweb.util.Util;
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
    public Autor save(Autor autor) throws ExceptionBBDD, ExceptionBadRequest {
        return getAutorOk(autorRepository.createSp(autor.getNombre()));
    }

    @Override
    public Autor update(Integer id, Autor autor) throws ExceptionBBDD, ExceptionBadRequest {
        return getAutorOk(autorRepository.updateSp(id, autor.getNombre()));
    }

    @Override
    public String enableStatus(int id) throws ExceptionBBDD {
        return getMessageStatus(autorRepository.changeStatusSp(id, Boolean.TRUE), Boolean.TRUE);
    }

    @Override
    public String disableStatus(int id) throws ExceptionBBDD {
        return getMessageStatus(autorRepository.changeStatusSp(id, Boolean.FALSE), Boolean.FALSE);
    }

    @Override
    public String getMessageStatus(String responseStatus, boolean status) throws ExceptionBBDD {
        isResponseOK(responseStatus);
        return status? "Autor Activado" : "Autor Desactivado";
    }

    public Autor getAutorOk(String response) throws ExceptionBBDD, ExceptionBadRequest {
        isResponseOK(response);
        int id = Util.getResponseId(response);
        return  autorRepository.findById(id).get();
    }

    private void isResponseOK(String response) throws ExceptionBBDD {
        if (!response.contains("OK")) {
            throw new ExceptionBBDD(response);
        }
    }

}
