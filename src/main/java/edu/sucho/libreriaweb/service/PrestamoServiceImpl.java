package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.Libro;
import edu.sucho.libreriaweb.model.Prestamo;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.repository.PrestamoRepository;
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
    @Transactional//HAY QUE CORREGIR ESTE METODO POR CAMBIOS EN ENTIDAD LIBRO // AVErIGUAR COMO IMPLEMENTEAR TO DO
    public boolean deleteByIdSoft(int id) throws ExceptionBBDD {
        try {
            Optional<Prestamo> prestamoOptional = prestamoRepository.findById(id);

            if(prestamoOptional.isPresent()){
                Prestamo prestamo = prestamoOptional.get();
                prestamo.setAlta(!prestamo.getAlta());
                
                Libro libroAAgregarPrestamo = libroService.devolverLibro(prestamo.getLibro().getId());
                libroService.update(libroAAgregarPrestamo.getId(), libroAAgregarPrestamo);

                prestamoRepository.save(prestamo);
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
    public List<Prestamo> findAllByAlta() throws ExceptionBBDD {
        try {
            Optional<List<Prestamo>> prestamosOptional = Optional.ofNullable(prestamoRepository.findAllByAlta());
            return prestamosOptional.get();
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    @Override
    public Boolean validarFieldUnique(String field) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
