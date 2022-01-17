package edu.sucho.libreriaweb.service.impl;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.dto.LibroDTO;
import edu.sucho.libreriaweb.model.dto.LibroRequestDTO;
import edu.sucho.libreriaweb.model.entity.Libro;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.repository.LibroRepository;
import edu.sucho.libreriaweb.service.inter.LibroService;
import edu.sucho.libreriaweb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl extends BaseServiceImpl<Libro, Integer> implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public LibroServiceImpl(BaseRepository<Libro, Integer> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> findAllByAlta() throws ExceptionBBDD {
        try {
            Optional<List<Libro>> librosOptional = Optional.ofNullable(libroRepository.findAllByAlta());
            return librosOptional.get();
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Libro findByIdAndAlta(int id) throws ExceptionBBDD {
        try {
            Optional<Libro> libroOptional = Optional.ofNullable(libroRepository.findByIdAndAlta(id));
            return libroOptional.get();
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    @Override
    public Libro save(LibroRequestDTO libro) throws ExceptionBBDD, ExceptionBadRequest {
        checkAnio(libro.getAnio());
        return getLibroOk(libroRepository.createSp(Boolean.TRUE,
                libro.getAnio(),
                libro.getEjemplares(),
                libro.getEjemplaresPrestados(),
                libro.getEjemplaresRestantes(),
                libro.getIsbn(),
                libro.getTitulo(),
                libro.getAutorId(),
                libro.getEditorialId()));

    }

    @Override
    public Libro update(int id, LibroRequestDTO libro) throws ExceptionBBDD, ExceptionBadRequest {
        checkAnio(libro.getAnio());
        return getLibroOk(libroRepository
                .updateSp(id,
                        libro.getTitulo(),
                        libro.getIsbn(),
                        libro.getAnio(),
                        libro.getEjemplares(),
                        libro.getEjemplaresPrestados(),
                        libro.getEjemplaresRestantes(),
                        libro.getAutorId(),
                        libro.getEditorialId()));
    }


    @Override
    public String disableStatus(int id) throws ExceptionBBDD {
        return getMessageStatus(libroRepository.changeStatusSp(id, Boolean.FALSE), Boolean.FALSE);
    }

    @Override
    public String enableStatus(int id) throws ExceptionBBDD {
        return getMessageStatus(libroRepository.changeStatusSp(id, true), true);
    }

    private Libro getLibroOk(String response) throws ExceptionBBDD, ExceptionBadRequest {
        isResponseOK(response);
        int id = Util.getResponseId(response);
        return libroRepository.findById(id).get();
    }

    private void isResponseOK(String response) throws ExceptionBBDD {
        if (!response.contains("OK")) {
            throw new ExceptionBBDD(response);
        }
    }

    @Override
    public String getMessageStatus(String responseStatus, boolean status) throws ExceptionBBDD {
        isResponseOK(responseStatus);
        return status ? "Libro Activado" : "Libro Desactivado";
    }

    public void checkAnio(Integer anio) throws ExceptionBadRequest {
        Date anioDate = new Date();
        Integer anioInt = anioDate.getYear() + 1900;
        System.out.println(anioInt);
        if (anio > anioInt) {
            throw new ExceptionBadRequest("El año de publicacion debe ser menor o igual al actual");
        }

    }

}