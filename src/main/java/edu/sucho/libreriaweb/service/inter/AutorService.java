package edu.sucho.libreriaweb.service.inter;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.entity.Autor;

import java.util.List;

public interface AutorService extends BaseService<Autor, Integer>{

    boolean deleteByIdSoft(int id) throws ExceptionBBDD;

    List<Autor> findAllByAlta() throws ExceptionBBDD;

    String enableStatus(int id) throws ExceptionBBDD;

    String disableStatus(int id) throws ExceptionBBDD;

    String getMessageStatus(String responseStatus, boolean status) throws ExceptionBBDD;
}
