package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.Prestamo;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.repository.PrestamoRepository;
import edu.sucho.libreriaweb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceImpl extends BaseServiceImpl<Prestamo, Integer> implements PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private LibroService libroService;

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
    public Prestamo save(Prestamo prestamo) throws ExceptionBBDD, ExceptionBadRequest {
        return getPrestamoOk(prestamoRepository
                .createSp(
                        prestamo.getCliente().getId(),
                        prestamo.getFechaDevolucion().getTime(),
                        prestamo.getFechaPrestamo().getTime(),
                        prestamo.getLibro().getId()
                ));
    }

    @Override
    public Prestamo update(Integer id, Prestamo prestamo) throws ExceptionBBDD, ExceptionBadRequest {
        return getPrestamoOk(prestamoRepository.updateSp(id, prestamo.getFechaDevolucion().getTime(),
                prestamo.getFechaPrestamo().getTime(), prestamo.getCliente().getId(),
                prestamo.getLibro().getId()));
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
}
