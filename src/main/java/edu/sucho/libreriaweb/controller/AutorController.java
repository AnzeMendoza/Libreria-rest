package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.Autor;
import edu.sucho.libreriaweb.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> save(@RequestBody Autor autor) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(autorService.save(autor));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Autor autor ){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(autorService.update(id,autor));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(autorService.delete(id));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }


 /*   @Autowired
    private AutorService autorService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(autorService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"Error\":\"No se encontro el recurso\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(autorService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"Error\":\"No fue encontrada\"}");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Autor autor) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(autorService.save(autor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"Error\":\"No se pudo guardar\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Autor autor) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(autorService.update(id, autor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"Error\":\"No se puede actualizar\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(autorService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"Error\":\"No se pudo eliminar\"}");
        }
    }*/

}
