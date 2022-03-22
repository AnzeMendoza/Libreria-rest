package edu.sucho.libreriaweb.service.inter;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.dto.AutorRequestDTO;
import edu.sucho.libreriaweb.model.dto.AutorResponseDTO;
import edu.sucho.libreriaweb.model.entity.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AutorService extends BaseService<Autor, Integer> {
    List<Autor> findAllByAlta() throws ExceptionBBDD;

    String enableStatus(int id) throws ExceptionBBDD;

    String disableStatus(int id) throws ExceptionBBDD;

    String getMessageStatus(String responseStatus, boolean status) throws ExceptionBBDD;

    Autor save(AutorRequestDTO autor) throws ExceptionBBDD, ExceptionBadRequest;

    Autor update(int id, AutorRequestDTO autor) throws ExceptionBBDD, ExceptionBadRequest;

    Page<AutorResponseDTO> getAll(Pageable pageable);
}