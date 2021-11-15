package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se han encontrdo editoriales para mostrar");
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
    public ResponseEntity<?> save(@RequestBody Editorial editorial) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(editorialService.saveEditorial(editorial));
        }
        catch(ExceptionBadRequest bR){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bR.getMessage());
        }
        catch(ExceptionBBDD bbdd){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bbdd.getMessage());

        }
    }
    //modificar 
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Editorial editorial) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(editorialService.update(id, editorial));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
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
