package edu.sucho.libreriaweb.service.impl;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.repository.EditorialRepository;
import edu.sucho.libreriaweb.service.inter.BaseService;
import edu.sucho.libreriaweb.service.inter.BaseValidationService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E, ID> implements BaseService<E, ID>, BaseValidationService {

    protected BaseRepository<E, ID> baseRepository;
    EditorialRepository eRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public E save(E entity) throws ExceptionBBDD, ExceptionBadRequest {
        Optional<E> entityOptional;
        try {
            entity = baseRepository.save(entity);
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
        return entity;
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws ExceptionBBDD, ExceptionBadRequest {
        Optional<E> entityOptional;
        E entityUpdate;
        entityOptional = baseRepository.findById(id);
        entityUpdate = entityOptional.get();
        entityUpdate = baseRepository.save(entity);
        return entityUpdate;
    }

    @Override
    @Transactional
    public Boolean delete(ID id) throws ExceptionBBDD {
        try {
            if (baseRepository.existsById(id)) {
                baseRepository.deleteById(id);
            } else {
                throw new ExceptionBBDD("");
            }
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public E findById(ID id) throws ExceptionBBDD, ExceptionBadRequest {
        try {
            Optional<E> entityEncontrada = baseRepository.findById(id);
            return entityEncontrada.get();
        } catch (NumberFormatException nfe){
            throw new ExceptionBadRequest("Tiene que recibir un entero");
        } catch (Exception e) {
            throw new ExceptionBBDD("No se encontro registro con ese id");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> findAll() throws ExceptionBBDD {
        List<E> entities;
        try {
            entities = baseRepository.findAll();
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
        return entities;
    }

    @Override
    @Transactional
    public Boolean validarFieldUnique(String field) {
        return (!(eRepository.findByValueField(field) == null));
    }
}
