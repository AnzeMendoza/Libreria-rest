package edu.sucho.libreriaweb.service.inter;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.dto.PrestamoRequestDTO;
import edu.sucho.libreriaweb.model.entity.Prestamo;

import java.util.List;

public interface PrestamoService extends BaseService<Prestamo, Integer> {
    List<Prestamo> findAllByAlta() throws ExceptionBBDD;

    String getMessageStatus(String responseStatus, boolean status) throws ExceptionBBDD;

    String disableStatus(int id) throws ExceptionBBDD;

    String enableStatus(int id) throws ExceptionBBDD;

    Prestamo save(PrestamoRequestDTO prestamo) throws ExceptionBBDD, ExceptionBadRequest;

    Prestamo update(Integer id, PrestamoRequestDTO prestamo) throws ExceptionBBDD, ExceptionBadRequest;

    List<Prestamo> prestamosPorIdCliente(Integer idCliente) throws ExceptionBBDD;
    }