package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.entity.Libro;
import edu.sucho.libreriaweb.model.mapper.ModelMapperDTO;
import edu.sucho.libreriaweb.service.inter.LibroService;
import edu.sucho.libreriaweb.util.Uri;
import edu.sucho.libreriaweb.util.Util;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Uri.LIBRO, produces = MediaType.APPLICATION_JSON_VALUE)
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private ModelMapperDTO modelMapperDTO;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperDTO.listLibroToDto(libroService.findAll()));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperDTO.libroToDto(libroService.findById(id)));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \""+e.getMessage()+"\"}");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \""+e.getMessage()+"\"}");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Libro libro,BindingResult result) throws ExceptionBadRequest {
        try {
                Util.ValidarParametros(result);
          return ResponseEntity.status(HttpStatus.CREATED)
                  .body(libroService.save(libro));
         }
        catch (ExceptionBBDD | ExceptionBadRequest e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(),e.getMessage(),Uri.LIBRO));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody Libro libro,BindingResult result )
            throws ExceptionBadRequest {
         try {
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(libroService.update(id,libro));
        } catch (ExceptionBBDD | ExceptionBadRequest ebd) {
            throw new ExceptionBadRequest(ebd.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(libroService.delete(id));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }
    
    @GetMapping("desactivar/{id}")
    private ResponseEntity<?> deactivate(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseInfo(HttpStatus.OK.value(), libroService.disableStatus(id),Uri.LIBRO_DESACTIVAR));
        } catch (ExceptionBBDD ebd) {
            throw new ExceptionBadRequest(ebd.getMessage());
        }
    }

    @GetMapping("activar/{id}")
    private ResponseEntity<?> activar(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseInfo(HttpStatus.OK.value(), libroService.enableStatus(id),Uri.LIBRO_ACTIVAR));
        } catch (ExceptionBBDD ebd) {
            throw new ExceptionBadRequest(ebd.getMessage());
        }
    }
}
