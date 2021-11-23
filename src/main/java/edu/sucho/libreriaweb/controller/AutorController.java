package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.Autor;
import edu.sucho.libreriaweb.service.AutorService;
import edu.sucho.libreriaweb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/autor", produces = MediaType.APPLICATION_JSON_VALUE)
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(autorService.findAll());
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(autorService.findById(id));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Autor autor, BindingResult result) throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(autorService.save(autor));
        } catch (ExceptionBBDD | ExceptionBadRequest ebd) {
            throw new ExceptionBadRequest(ebd.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody Autor autor, BindingResult result) throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(autorService.update(id, autor));
        } catch (ExceptionBBDD | ExceptionBadRequest ebd) {
            throw new ExceptionBadRequest(ebd.getMessage());
        }
    }

    @GetMapping("activar/{id}")
    private ResponseEntity<?> active(@PathVariable("id") int id) throws ExceptionBadRequest {
//        try {
//            return ResponseEntity.status(HttpStatus.ok).body(new ResponseInfo(HttpStatus.OK.value(),  ));
//        }catch(ExceptionBadRequest e){
//            
//        }
        return null;
    }

}
