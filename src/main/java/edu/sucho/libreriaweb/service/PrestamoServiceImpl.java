package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.Libro;
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
        System.out.println("#################");
        System.out.println(prestamo.getFechaDevolucion());
        System.out.println(prestamo.getFechaPrestamo());
        System.out.println("#################");
        return getPrestamoOk(prestamoRepository
                .createSp(
                        prestamo.getCliente().getId(),
                        prestamo.getFechaDevolucion(),
                        prestamo.getFechaPrestamo(),
                        prestamo.getLibro().getId()
                ));
    }

    @Override
    public Prestamo update(Integer id, Prestamo prestamo) throws ExceptionBBDD, ExceptionBadRequest {
        return getPrestamoOk(prestamoRepository.updateSp(id, prestamo.getFechaDevolucion(),
                prestamo.getFechaPrestamo(), prestamo.getCliente().getId(),
                prestamo.getLibro().getId()));
    }

    private Prestamo getPrestamoOk(String response) throws ExceptionBBDD, ExceptionBadRequest {
        isResponseOK(response);
        int id = Util.getResponseId(response);
        return prestamoRepository.findById(id).get();
    }

    private void isResponseOK(String response) throws ExceptionBBDD {
        if (!response.contains("OK")) {
            throw new ExceptionBBDD(response);
        }
    }
}
