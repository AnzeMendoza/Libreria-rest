package edu.sucho.libreriaweb.service.impl;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.entity.Editorial;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.repository.EditorialRepository;
import edu.sucho.libreriaweb.service.inter.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialServiceImpl extends BaseServiceImpl<Editorial, Integer> implements EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    public EditorialServiceImpl(BaseRepository<Editorial, Integer> baseRepository) {
        super(baseRepository);
    }


    @Override
    @Transactional
    public boolean deleteByIdSoft(int id) throws ExceptionBBDD {
        try {
            Optional<Editorial> editorialOptional = editorialRepository.findById(id);

            if (editorialOptional.isPresent()) {
                Editorial editorial = editorialOptional.get();
                editorial.setAlta(!editorial.getAlta());
                editorialRepository.save(editorial);
            } else {
                throw new ExceptionBBDD("deleteByIdSoft");
            }
            return true;
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    @Override
    public List<Editorial> findAllByAlta() throws ExceptionBBDD {
        try {
            Optional<List<Editorial>> editorialesOptional 
                    = Optional.ofNullable(editorialRepository.findAllByAlta());
            return editorialesOptional.get();
        } catch (Exception e) {
            throw new ExceptionBBDD(e.getMessage());
        }
    }

    @Override
    public Editorial save(Editorial editorial) throws ExceptionBBDD{
         return retornarEditorial(editorialRepository.saveEditorial(editorial.getNombre()));
    }

    @Override
    public Editorial update(Integer id, Editorial editorial) throws ExceptionBBDD {
    return retornarEditorial(editorialRepository.updateEditorial(id,editorial.getNombre()));
    }

    public String changeStatus(int id, Boolean estado)throws ExceptionBBDD{
       return   retornarMensaje(editorialRepository.changeStatus(id,estado),estado);
    }

    private String retornarMensaje(String resultado, Boolean estado) throws ExceptionBBDD {
        if(resultado.contains("OK")){
            return (estado)?"Editorial Activado": "Editorial Desactivado";
        }
        throw new ExceptionBBDD(resultado);
    }

    private Editorial retornarEditorial(String resultado) throws ExceptionBBDD {
        if(resultado.contains("OK")){
            int id = Integer.parseInt(resultado.split(",")[1]);
            return  editorialRepository.findById(id).get();
        }
        throw new ExceptionBBDD(resultado);
    }


}
