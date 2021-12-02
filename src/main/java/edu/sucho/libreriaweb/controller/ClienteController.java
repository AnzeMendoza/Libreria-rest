package edu.sucho.libreriaweb.controller;



import edu.sucho.libreriaweb.config.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.entity.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.sucho.libreriaweb.service.inter.ClienteService;
import edu.sucho.libreriaweb.util.Uri;
import edu.sucho.libreriaweb.util.Util;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        try {
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
        }
        catch( ExceptionBBDD ebd){
            throw new ExceptionBadRequest(ebd.getMessage());
        }
        catch (ExceptionBadRequest ebr){
            throw new ExceptionBadRequest(ebr.getMessage());

        }

    }
     @PutMapping("/{id}")
    public ResponseEntity<?> update( @PathVariable("id") int id,@Valid @RequestBody Cliente cliente, BindingResult result) throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.update(id,cliente));
        } catch (ExceptionBBDD ebd) {
            throw new ExceptionBadRequest(ebd.getMessage());
        }
        catch (ExceptionBadRequest ebr){
            throw new ExceptionBadRequest(ebr.getMessage());

        }

    }

   
    @GetMapping("activar/{id}")
    private ResponseEntity<?> active(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseInfo(HttpStatus.OK.value(),clienteService.changeStatus(id,Boolean.TRUE),Uri.CLIENTE_ACTIVAR));

        } catch (ExceptionBBDD ebd) {
            throw new ExceptionBadRequest(ebd.getMessage());
        }
    }

    @GetMapping("desactivar/{id}")
    private ResponseEntity<?> desactive(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseInfo(HttpStatus.OK.value(),clienteService.changeStatus(id,Boolean.FALSE),Uri.CLIENTE_DESACTIVAR));

        } catch (ExceptionBBDD ebd) {
            throw new ExceptionBadRequest(ebd.getMessage());
        }
    }
    
    
}
