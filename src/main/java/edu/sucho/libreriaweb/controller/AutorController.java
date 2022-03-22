package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.dto.AutorRequestDTO;
import edu.sucho.libreriaweb.model.entity.Autor;
import edu.sucho.libreriaweb.model.mapper.ModelMapperDTO;
import edu.sucho.libreriaweb.service.inter.AutorService;
import edu.sucho.libreriaweb.util.Uri;
import edu.sucho.libreriaweb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Uri.AUTOR, produces = MediaType.APPLICATION_JSON_VALUE)
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Autowired
    private ModelMapperDTO modelMapperDTO;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return responseAutors(autorService.findAll());
        } catch (ExceptionBBDD ebd) {
            return responseExceptionBBDD(ebd, Uri.AUTOR);
        }
    }

    @GetMapping("/paged")
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok().body(autorService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") int id) {
        try {
            return responseAutor(id);
        } catch (ExceptionBBDD | ExceptionBadRequest ebd) {
            return responseExceptionBBDD(ebd, Uri.AUTOR + "/" + id);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody AutorRequestDTO autor,
                                  BindingResult result) throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return responseSaveAutor(autor);
        } catch (ExceptionBBDD ebd) {
            return responseExceptionBBDD(ebd, Uri.AUTOR);
        } catch (ExceptionBadRequest ebr) {
            return responseExceptionBadRequest(ebr, Uri.AUTOR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id,
                                    @Valid @RequestBody AutorRequestDTO autor,
                                    BindingResult result) throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return responseAutorUpdate(id, autor);
        } catch (ExceptionBBDD ebd) {
            return responseExceptionBBDD(ebd, Uri.AUTOR + "/" + id);
        } catch (ExceptionBadRequest ebr) {
            return responseExceptionBadRequest(ebr, Uri.AUTOR + "/" + id);
        }
    }

    @GetMapping("/activar/{id}")
    public ResponseEntity<?> active(@PathVariable("id") int id) {

        try {
            return responseAutorEnable(id);
        } catch (ExceptionBBDD ebd) {
            return responseExceptionBBDD(ebd, Uri.AUTOR_ACTIVAR + "/" + id);
        }
    }

    @GetMapping("/desactivar/{id}")
    public ResponseEntity<?> deactivate(@PathVariable("id") int id) {

        try {
            return responseAutorDisable(id);
        } catch (ExceptionBBDD ebd) {
            return responseExceptionBBDD(ebd, Uri.AUTOR_DESACTIVAR + "/" + id);
        }
    }

    private ResponseEntity<?> responseAutors(List<Autor> autors) throws ExceptionBBDD {
        return ResponseEntity.status(HttpStatus.OK).body(modelMapperDTO.listAutorToDto(autors));
    }

    private ResponseEntity<?> responseSaveAutor(AutorRequestDTO autor) throws ExceptionBBDD, ExceptionBadRequest {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapperDTO.autorToDto(autorService.save(autor)));
    }

    private ResponseEntity<?> responseAutorEnable(int id) throws ExceptionBBDD {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseInfo(HttpStatus.OK.value(), autorService.enableStatus(id), String.format("%s/%d", Uri.AUTOR_ACTIVAR, id)));
    }

    private ResponseEntity<?> responseAutorDisable(int id) throws ExceptionBBDD {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseInfo(HttpStatus.OK.value(), autorService.disableStatus(id), String.format("%s/%d", Uri.AUTOR_DESACTIVAR, id)));
    }

    private ResponseEntity<?> responseAutor(int id) throws ExceptionBBDD, ExceptionBadRequest {
        return ResponseEntity.status(HttpStatus.OK).body(modelMapperDTO.autorToDto(autorService.findById(id)));
    }

    private ResponseEntity<?> responseAutorUpdate(int id, AutorRequestDTO autor) throws ExceptionBBDD, ExceptionBadRequest {
        return ResponseEntity.status(HttpStatus.OK).body(modelMapperDTO.autorToDto(autorService.update(id, autor)));
    }

    private ResponseEntity<?> responseExceptionBBDD(Exception exception, String path) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), path));
    }

    private ResponseEntity<?> responseExceptionBadRequest(Exception exception, String path) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), path));
    }
}