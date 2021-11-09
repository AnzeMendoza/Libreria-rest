package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.Libro;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.repository.LibroRepository;
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
            Optional<List<Libro>> librosOptional = Optional.of(libroRepository.findAllByAlta());
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
    public boolean deleteByIdSoft(int id) throws ExceptionBBDD {
        try {
            Optional<Libro> libroOptional = libroRepository.findById(id);

            if(libroOptional.isPresent()){
                Libro libro = libroOptional.get();
                libro.setAlta(!libro.getAlta());
                libroRepository.save(libro);
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
    public List<Libro> findAllByAltaAndInStock() throws ExceptionBBDD {
        try {
         Optional<List<Libro>> librosOptional = Optional.ofNullable(libroRepository.findAllByAltaAndInStock());
         return librosOptional.get();
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    @Override
    public Libro substractOneLibro(int id) throws ExceptionBBDD {
        try {
            Optional<Libro> libroOptional = Optional.ofNullable(libroRepository.findByIdAndAlta(id));
            Libro libroAextraerUnEjemplar = libroOptional.get();
            libroAextraerUnEjemplar.setEjemplaresPrestados(libroAextraerUnEjemplar.getEjemplaresPrestados() + 1);
            libroAextraerUnEjemplar.setEjemplaresRestantes(libroAextraerUnEjemplar.getEjemplares() - libroAextraerUnEjemplar.getEjemplaresPrestados());
            return libroAextraerUnEjemplar;
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    @Override
    public Libro addOneLibro(int id) throws ExceptionBBDD {
        try {
            Optional<Libro> libroOptional = Optional.ofNullable(libroRepository.findByIdAndAlta(id));
            Libro libroAextraerUnEjemplar = libroOptional.get();
            libroAextraerUnEjemplar.setEjemplaresPrestados(libroAextraerUnEjemplar.getEjemplaresPrestados() - 1);
            libroAextraerUnEjemplar.setEjemplaresRestantes(libroAextraerUnEjemplar.getEjemplares() + libroAextraerUnEjemplar.getEjemplaresPrestados());
            return libroAextraerUnEjemplar;
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }
}
