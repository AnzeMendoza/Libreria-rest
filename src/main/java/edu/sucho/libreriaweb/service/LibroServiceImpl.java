package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.Autor;
import edu.sucho.libreriaweb.model.Libro;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.repository.LibroRepository;
import edu.sucho.libreriaweb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<Libro> findByTitulo(String titulo) throws ExceptionBBDD {
        try {
            Optional<List<Libro>> librosOptional = Optional.ofNullable(libroRepository.findByTitulo(titulo));
            return librosOptional.get();
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean disableById(int id) throws ExceptionBBDD {
        try {
            Optional<Libro> libroOptional = libroRepository.findById(id);

            if (!libroOptional.isPresent()) {
                throw new ExceptionBBDD("No se encontro el libro con ese Id");
            }
            Libro libro = libroOptional.get();
            if (!libro.getAlta()) {
                throw new ExceptionBBDD("Ya esta dado de baja");
            }

            libro.setAlta(Boolean.FALSE);
            libroRepository.save(libro);
            return true;
        } catch (ExceptionBBDD e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean enableById(int id) throws ExceptionBBDD {
        try {
            Optional<Libro> libroOptional = libroRepository.findById(id);

            if (!libroOptional.isPresent()) {
                throw new ExceptionBBDD("No se encontro el libro con ese Id");
            }
            Libro libro = libroOptional.get();
            if (libro.getAlta()) {
                throw new ExceptionBBDD("Ya esta dado de alta");
            }

            libro.setAlta(Boolean.TRUE);
            libroRepository.save(libro);
            return true;
        } catch (ExceptionBBDD e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> findAllByAltaAndInStock() throws ExceptionBBDD {
        try {
            Optional<List<Libro>> librosOptional = Optional.ofNullable(libroRepository.findAllByAltaAndInStock());
            return librosOptional.get();
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Libro save(Libro libro) throws ExceptionBBDD, ExceptionBadRequest {
/*
        if(libro.getAutor().getId()==null){
            throw new ExceptionBadRequest("El id de Autor es null");
        }

        if(libro.getEditorial().getId()==null){
            throw new ExceptionBadRequest("El id de Editorial es null");
        }
*/

        return getLibroOk(libroRepository
                .createSp(Boolean.TRUE,
                        libro.getAnio(),
                        libro.getEjemplares(),
                        libro.getEjemplaresPrestados(),
                        libro.getEjemplaresRestantes(),
                        libro.getIsbn(),
                        libro.getTitulo(),
                        libro.getAutor().getId(),
                        libro.getEditorial().getId()
                        ));
    }

    @Override
    public Libro prestarLibro(int id) throws ExceptionBBDD {
        try {
            Libro libro = libroRepository.findByIdAndAlta(id);
            if (libro == null) {
                throw new ExceptionBBDD("No existe ese libro");
            }
            libro.actualizarStockPostPrestamo();
            return libro;
        } catch (ExceptionBBDD e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }
    
    @Override
    public Libro devolverLibro(int id) throws ExceptionBBDD {
        try {
            Libro libro = libroRepository.findByIdAndAlta(id);
            if (libro == null) {
                throw new ExceptionBBDD("No existe ese libro");
            }
            libro.actualizarStockPostDevolucion();
            return libro;
        } catch (ExceptionBBDD e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    private Libro getLibroOk(String response) throws ExceptionBBDD, ExceptionBadRequest {
        isResponseOK(response);
        int id = Util.getResponseId(response);
        return  libroRepository.findById(id).get();
    }

    private void isResponseOK(String response) throws ExceptionBBDD {
        if (!response.contains("OK")) {
            throw new ExceptionBBDD(response);
        }
    }
}
