
package edu.sucho.libreriaweb.controller;
import org.springframework.beans.factory.annotation.Autowired;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.sucho.libreriaweb.service.ClienteService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll());
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }
     @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Cliente cliente) {
        try {
   
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Cliente cliente ){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.update(id,cliente));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.delete(id));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }
    
    
    
}
