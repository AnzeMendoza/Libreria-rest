package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import edu.sucho.libreriaweb.service.EditorialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import edu.sucho.libreriaweb.model.Editorial;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/editorial", produces = MediaType.APPLICATION_JSON_VALUE)

public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() throws ExceptionBBDD {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(editorialService.findAll());
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(editorialService.findById(id));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }



    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Editorial editorial, BindingResult result) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(editorialService.save(editorial));
        }
        catch( ExceptionBBDD ebd){
            throw new ExceptionBadRequest(ebd.getMessage());
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update( @PathVariable("id") int id,@Valid @RequestBody Editorial editorial, BindingResult result) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(editorialService.update(id,editorial));
        } catch (ExceptionBBDD ebd) {
            throw new ExceptionBadRequest(ebd.getMessage());
        }

    }

    @GetMapping("activar/{id}")
    private ResponseEntity<?> active(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseInfo(HttpStatus.OK.value(),editorialService.changeStatus(id,Boolean.TRUE),"/api/v1/editorial/activar",new Date()));
        } catch (ExceptionBBDD ebd) {
            throw new ExceptionBadRequest(ebd.getMessage());
        }
    }

    @GetMapping("desactivar/{id}")
    private ResponseEntity<?> desactive(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseInfo(HttpStatus.OK.value(),editorialService.changeStatus(id,Boolean.FALSE),"/api/v1/editorial/activar",new Date()));
        } catch (ExceptionBBDD ebd) {
            throw new ExceptionBadRequest(ebd.getMessage());
        }
    }




}
