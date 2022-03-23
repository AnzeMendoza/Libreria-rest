package edu.sucho.libreriaweb.service.inter;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.dto.EditorialRequestDTO;
import edu.sucho.libreriaweb.model.entity.Editorial;

import java.util.List;

public interface EditorialService extends BaseService<Editorial, Integer>{
    boolean deleteByIdSoft(int id) throws ExceptionBBDD;

    List<Editorial> findAllByAlta() throws ExceptionBBDD;

    Editorial save(EditorialRequestDTO editorialDTORequest) throws ExceptionBBDD;

    Editorial update (Integer id, EditorialRequestDTO editorialDTORequest) throws ExceptionBBDD;

    String changeStatus(int id, Boolean estado)throws ExceptionBBDD;
}
