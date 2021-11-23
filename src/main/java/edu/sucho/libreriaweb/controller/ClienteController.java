
package edu.sucho.libreriaweb.controller;
import org.springframework.beans.factory.annotation.Autowired;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
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
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
    public ResponseEntity<?> save(@Valid @RequestBody Cliente cliente, BindingResult result) throws ExceptionBadRequest {
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
          Cliente client = clienteService.saveCliente(cliente);
          return ResponseEntity.status(HttpStatus.CREATED).body(client);
        }
        catch(ExceptionBBDD bbdd){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bbdd.getMessage());
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
