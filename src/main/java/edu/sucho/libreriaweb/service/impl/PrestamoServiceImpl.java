package edu.sucho.libreriaweb.service.impl;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.dto.PrestamoRequestDTO;
import edu.sucho.libreriaweb.model.entity.Prestamo;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.repository.PrestamoRepository;
import edu.sucho.libreriaweb.service.inter.ClienteService;
import edu.sucho.libreriaweb.service.inter.LibroService;
import edu.sucho.libreriaweb.service.inter.PrestamoService;
import edu.sucho.libreriaweb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceImpl extends BaseServiceImpl<Prestamo, Integer> implements PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private  LibroService libroService;

    @Autowired
    private  ClienteService clienteService;

    public PrestamoServiceImpl(BaseRepository<Prestamo, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Prestamo> findAllByAlta() throws ExceptionBBDD {
        try {
            Optional<List<Prestamo>> prestamosOptional = Optional.ofNullable(prestamoRepository.findAllByAlta());
            return prestamosOptional.get();
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    @Override
    public Prestamo save(PrestamoRequestDTO prestamo) throws ExceptionBBDD, ExceptionBadRequest {
               return getPrestamoOk(prestamoRepository
                .createSp(
                        clienteService.findIdByDocumento(prestamo.getDniCliente()),
                        prestamo.getFechaDevolucion().getTime(),
                        prestamo.getFechaPrestamo().getTime(),
                        libroService.findIdByIsbnOrTitulo(prestamo.getTituloLibro(),prestamo.getIsbn())
                ));
    }

    @Override
    public Prestamo update(Integer id, PrestamoRequestDTO prestamo) throws ExceptionBBDD, ExceptionBadRequest {
        return getPrestamoOk(prestamoRepository.updateSp(id,
                prestamo.getFechaDevolucion().getTime(),
                prestamo.getFechaPrestamo().getTime(),
                clienteService.findIdByDocumento(prestamo.getDniCliente()),
                libroService.findIdByIsbnOrTitulo(prestamo.getTituloLibro(),prestamo.getIsbn()))
                );
    }

    @Override
    public List<Prestamo> prestamosPorIdCliente(Integer idCliente) throws ExceptionBBDD {
        return prestamoRepository.prestamosPorIdCliente(idCliente);
    }

    private Prestamo getPrestamoOk(String response) throws ExceptionBBDD, ExceptionBadRequest {
        isResponseOK(response);
        int id = Util.getResponseId(response);
        return prestamoRepository.findById(id).get();
    }

    public String disableStatus(int id) throws ExceptionBBDD {
        return getMessageStatus(prestamoRepository.changeStatusSp(id, Boolean.FALSE), Boolean.FALSE);
    }

    @Override
    public String enableStatus(int id) throws ExceptionBBDD {
        return getMessageStatus(prestamoRepository.changeStatusSp(id, true), true);
    }

    private void isResponseOK(String response) throws ExceptionBBDD {
        if (!response.contains("OK")) {
            throw new ExceptionBBDD(response);
        }
    }

    @Override
    public String getMessageStatus(String responseStatus, boolean status) throws ExceptionBBDD {
        isResponseOK(responseStatus);
        return status ? "Prestamo Activado" : "Prestamo Desactivado";
    }

    @Override
    public Page<Prestamo> findAllByAlta(Pageable pageable) throws ExceptionBBDD {
        try{
            Page<Prestamo> entities = prestamoRepository.findAllByAlta(pageable);
            return entities;
        } catch (Exception e){
            throw new ExceptionBBDD("problemas al querer paginar");
        }
    }
}
