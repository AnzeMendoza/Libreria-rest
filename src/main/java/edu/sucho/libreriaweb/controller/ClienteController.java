package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.entity.Cliente;
import edu.sucho.libreriaweb.model.mapper.ModelMapperDTO;
import edu.sucho.libreriaweb.security.JWT;
import edu.sucho.libreriaweb.service.inter.ClienteService;
import edu.sucho.libreriaweb.util.Uri;
import edu.sucho.libreriaweb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Uri.CLIENTE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapperDTO modelMapperDTO;

    @Autowired
    private JWT jwt_service;

    @GetMapping("/")
    public ResponseEntity<?> getAll(@RequestHeader("jwt") String jwt) {
        try {
            if(clienteService.findByUsername(jwt_service.getValue(jwt))==null){
                throw new ExceptionBBDD("no se encontro el cliente en la BBDD");
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperDTO.listClienteToDto(clienteService.findAll()));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Uri.CLIENTE));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperDTO.clienteToDto(clienteService.findById(id)));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), String.format("%s/%d",Uri.CLIENTE,id)));
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Cliente cliente, BindingResult result)
            throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
        } catch (ExceptionBBDD | ExceptionBadRequest e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Uri.CLIENTE));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody Cliente cliente, BindingResult result)
            throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.update(id, cliente));
        } catch (ExceptionBBDD | ExceptionBadRequest e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), String.format("%s/%d",Uri.CLIENTE,id)));

        }

    }

    @GetMapping("activar/{id}")
    private ResponseEntity<?> active(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseInfo(HttpStatus.OK.value(), clienteService.changeStatus(id, Boolean.TRUE), String.format("%s/%d",Uri.CLIENTE_ACTIVAR,id)));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), String.format("%s/%d",Uri.CLIENTE_ACTIVAR,id)));
        }
    }

    @GetMapping("desactivar/{id}")
    private ResponseEntity<?> desactive(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseInfo(HttpStatus.OK.value(), clienteService.changeStatus(id, Boolean.FALSE), String.format("%s/%d",Uri.CLIENTE_DESACTIVAR,id)));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), String.format("%s/%d",Uri.CLIENTE_DESACTIVAR,id)));
        }
    }
    

}
