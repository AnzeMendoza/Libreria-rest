package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.dto.LibroRequestDTO;
import edu.sucho.libreriaweb.model.entity.Libro;
import edu.sucho.libreriaweb.model.mapper.ModelMapperDTO;
import edu.sucho.libreriaweb.service.inter.LibroService;
import edu.sucho.libreriaweb.util.Uri;
import edu.sucho.libreriaweb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
            return responseLibros(libroService.findAll());
        } catch (ExceptionBBDD e) {
            return responseExceptionBadRequest(e, Uri.LIBRO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") int id) {
        try {
            return responseLibro(id);
        } catch (ExceptionBBDD ebd) {
            return responseExceptionBBDD(ebd, String.format("%s/%d", Uri.LIBRO, id));
        }
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody LibroRequestDTO libro, BindingResult result) throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return responseSaveLibro(libro);
        } catch (ExceptionBBDD ebd) {
            return responseExceptionBBDD(ebd, Uri.LIBRO);
        } catch (ExceptionBadRequest ebr) {
            return responseExceptionBadRequest(ebr, Uri.LIBRO);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody LibroRequestDTO libro, BindingResult result)
            throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return responseUpdateLibro(id, libro);
        } catch (ExceptionBBDD ebd) {
            return responseExceptionBBDD(ebd, String.format("%s/%d", Uri.LIBRO, id));
        } catch (ExceptionBadRequest ebr) {
            return responseExceptionBadRequest(ebr, String.format("%s/%d", Uri.LIBRO, id));
        }
    }

    @GetMapping("activar/{id}")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> activar(@PathVariable("id") int id) {
        try {
            return responseLibroEnable(id);
        } catch (ExceptionBBDD ebd) {
            return responseExceptionBBDD(ebd, String.format("%s/%d", Uri.LIBRO_ACTIVAR, id));
        }
    }

    @GetMapping("desactivar/{id}")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> desactivar(@PathVariable("id") int id) {
        try {
            return responseLibroDisable(id);
        } catch (ExceptionBBDD ebd) {
            return responseExceptionBBDD(ebd, Uri.LIBRO_DESACTIVAR + "/" + id);
        }
    }

    private ResponseEntity<?> responseLibros(List<Libro> libros) throws ExceptionBBDD {
        return ResponseEntity.status(HttpStatus.OK).body(modelMapperDTO.listLibroToDto(libros));
    }

    private ResponseEntity<?> responseLibro(int id) throws ExceptionBBDD {
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapperDTO.libroToDto(libroService.findById(id)));
    }

    private ResponseEntity<?> responseSaveLibro(LibroRequestDTO libro) throws ExceptionBBDD, ExceptionBadRequest {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapperDTO.libroToDto(libroService.save(libro)));
    }

    private ResponseEntity<?> responseUpdateLibro(int id, LibroRequestDTO libro) throws ExceptionBBDD, ExceptionBadRequest {
        return ResponseEntity.status(HttpStatus.OK).body(modelMapperDTO.libroToDto(libroService.update(id, libro)));
    }

    private ResponseEntity<?> responseLibroEnable(int id) throws ExceptionBBDD {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseInfo(HttpStatus.OK.value(),
                libroService.enableStatus(id), String.format("%s/%d", Uri.LIBRO_ACTIVAR, id)));
    }

    private ResponseEntity<?> responseLibroDisable(int id) throws ExceptionBBDD {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseInfo(HttpStatus.OK.value(),
                libroService.disableStatus(id), String.format("%s/%d", Uri.LIBRO_DESACTIVAR, id)));
    }

    private ResponseEntity<?> responseExceptionBBDD(Exception exception, String path) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), path));
    }

    private ResponseEntity<?> responseExceptionBadRequest(Exception exception, String path) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), path));
    }
}
