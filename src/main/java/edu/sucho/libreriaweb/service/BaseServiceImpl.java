package edu.sucho.libreriaweb.service;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.repository.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E, ID> implements BaseService<E, ID> {

    protected BaseRepository<E, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public E save(E entity) throws ExceptionBBDD {
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
    public E update(ID id, E entity) throws ExceptionBBDD {
        Optional<E> entityOptional;
        E entityUpdate;
        try {
            entityOptional = baseRepository.findById(id);
            entityUpdate = entityOptional.get();
            entityUpdate = baseRepository.save(entity);
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
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
    public E findById(ID id) throws ExceptionBBDD {
        Optional<E> entityEncontrada;
        try {
            entityEncontrada = baseRepository.findById(id);
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
        return entityEncontrada.get();
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

    // TODO se puede pasar a generico pero las entidades tiene que extender de clase base, ver despues
/*    @Override
    @Transactional
    public Boolean deleteByIdSoft(ID id, E entity) throws ExceptionBBDD {
        try {
            Optional<E> entityOptional = baseRepository.findById(id);
            if (entityOptional.isPresent()) {
                E entityDelete = entityOptional.get();
                entity.setAlta(!entity.getAlta());
                baseRepository.save(entityDelete);
            } else {
                throw new ExceptionBBDD("deleteByIdSoft");
            }
            return true;
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }*/
}
