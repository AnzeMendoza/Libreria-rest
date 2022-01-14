package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.entity.Autor;
import edu.sucho.libreriaweb.model.mapper.ModelMapperDTO;
import edu.sucho.libreriaweb.service.inter.AutorService;
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
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperDTO.listAutorToDto(autorService.findAll()));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value()
                            , e.getMessage(), Uri.AUTOR));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperDTO.autorToDto(autorService.findById(id)));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value()
                            , e.getMessage(), String.format("/%s/%d", Uri.AUTOR,id)));
        }
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody Autor autor, BindingResult result) throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(autorService.save(autor));
        } catch (ExceptionBBDD | ExceptionBadRequest ebd) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value()
                            , ebd.getMessage(), Uri.AUTOR));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody Autor autor, BindingResult result) throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(autorService.update(id,autor));

        } catch (ExceptionBBDD | ExceptionBadRequest ebd) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value()
                            , ebd.getMessage(), String.format("/%s/%d", Uri.AUTOR,id)));
        }
    }

    @GetMapping("activar/{id}")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> active(@PathVariable("id") int id) throws ExceptionBadRequest{
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseInfo(HttpStatus.OK.value()
                            , autorService.enableStatus(id), String.format("%s/%d", Uri.AUTOR_ACTIVAR,id)));
        } catch (ExceptionBBDD ebd){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value()
                            , ebd.getMessage(), String.format("%s/%d", Uri.AUTOR_ACTIVAR,id)));
        }
    }

    @GetMapping("desactivar/{id}")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deactivate(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseInfo(HttpStatus.OK.value()
                            , autorService.disableStatus(id),String.format("%s/%d", Uri.AUTOR_DESACTIVAR,id)));
        } catch (ExceptionBBDD ebd) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value()
                            , ebd.getMessage(), String.format("%s/%d", Uri.AUTOR_DESACTIVAR,id)));
        }
    }
}
