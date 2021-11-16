package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.sucho.libreriaweb.service.EditorialService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import edu.sucho.libreriaweb.model.Editorial;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/editorial", produces = MediaType.APPLICATION_JSON_VALUE)

public class EditorialController {

    @Autowired
    private EditorialService editorialService;
     //mostrar todo
    @GetMapping("/")
    public ResponseEntity<?> getAll() throws ExceptionBBDD {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(editorialService.findAll());
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }
    //mostrar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(editorialService.findById(id));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }

    //guardar
    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Editorial editorial, BindingResult result) throws ExceptionBadRequest {
       String nombre ="";
        try {
          // valida a nivel  datos,Java
          if (result.hasErrors()) {
             List<ObjectError> oEs =result.getAllErrors().stream().collect(Collectors.toList());
             String err ="";
             for (ObjectError oE:  oEs){
                FieldError fieldError = (FieldError)oE;
                err +=fieldError.getField() + " : " + fieldError.getDefaultMessage();
            }
            throw new ExceptionBadRequest(err);
          }
          Editorial edi = editorialService.saveEditorial(editorial);
          return ResponseEntity.status(HttpStatus.CREATED).body(edi);
        }
        catch(ExceptionBBDD bbdd){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bbdd.getMessage());
        }

    }

    //modificar 
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, 
            @RequestBody Editorial editorial) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(editorialService.updateEditorial(id,editorial));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\" : \"error\"}");
        }
    }



    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(editorialService.delete(id));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }

}
