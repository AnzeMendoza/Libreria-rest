package edu.sucho.libreriaweb.service.impl;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.dto.AutorRequestDTO;
import edu.sucho.libreriaweb.model.dto.AutorResponseDTO;
import edu.sucho.libreriaweb.model.entity.Autor;
import edu.sucho.libreriaweb.model.mapper.ModelMapperDTO;
import edu.sucho.libreriaweb.repository.AutorRepository;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.service.inter.AutorService;
import edu.sucho.libreriaweb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl extends BaseServiceImpl<Autor, Integer> implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ModelMapperDTO modelMapperDTO;

    public AutorServiceImpl(BaseRepository<Autor, Integer> baseRepository) {
        super(baseRepository);
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
    public Autor save(AutorRequestDTO autor) throws ExceptionBBDD, ExceptionBadRequest {
        return getAutorOk(autorRepository.createSp(autor.getNombre()));
    }

    @Override
    public Autor update(int id, AutorRequestDTO autor) throws ExceptionBBDD, ExceptionBadRequest {
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
        return status ? "Autor Activado" : "Autor Desactivado";
    }

    public Autor getAutorOk(String response) throws ExceptionBBDD, ExceptionBadRequest {
        isResponseOK(response);
        int id = Util.getResponseId(response);
        return autorRepository.findById(id).get();
    }

    private void isResponseOK(String response) throws ExceptionBBDD {
        if (!response.contains("OK")) {
            throw new ExceptionBBDD(response);
        }
    }

    @Override
    public Page<AutorResponseDTO> getAll(Pageable pageable) {
        Page<Autor> entities = autorRepository.findAll(pageable);
        Page<AutorResponseDTO> dtoPage = entities.map(autor -> modelMapperDTO.autorToDto(autor));
        return dtoPage;
    }
}
