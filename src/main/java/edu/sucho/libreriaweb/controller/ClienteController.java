package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.dto.ClienteRequestDTO;
import edu.sucho.libreriaweb.model.entity.Cliente;
import edu.sucho.libreriaweb.model.mapper.ModelMapperDTO;
import edu.sucho.libreriaweb.service.inter.ClienteService;
import edu.sucho.libreriaweb.util.TokenProvider;
import edu.sucho.libreriaweb.util.Uri;
import edu.sucho.libreriaweb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Uri.CLIENTE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ModelMapperDTO modelMapperDTO;

    @Autowired
    private TokenProvider tokenProvider;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperDTO.listClienteToDto(clienteService.findAll()));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Uri.CLIENTE));
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN') OR @userAccess.hasUserId(authentication,#id)")
    public ResponseEntity<?> getOne(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(modelMapperDTO.clienteToDto(clienteService.findById(id)));
        } catch (ExceptionBBDD | ExceptionBadRequest e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), String.format("%s/%d", Uri.CLIENTE, id)));
        }
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO, BindingResult result)
            throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteRequestDTO));
        } catch (ExceptionBBDD | ExceptionBadRequest e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Uri.CLIENTE));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody Cliente cliente, BindingResult result)
            throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.update(id, cliente));
        } catch (ExceptionBBDD | ExceptionBadRequest e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), String.format("%s/%d", Uri.CLIENTE, id)));
        }
    }

    @GetMapping("activar/{id}")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> active(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseInfo(HttpStatus.OK.value(), clienteService.changeStatus(id, Boolean.TRUE), String.format("%s/%d", Uri.CLIENTE_ACTIVAR, id)));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), String.format("%s/%d", Uri.CLIENTE_ACTIVAR, id)));
        }
    }

    @GetMapping("desactivar/{id}")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> desactive(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseInfo(HttpStatus.OK.value(), clienteService.changeStatus(id, Boolean.FALSE), String.format("%s/%d", Uri.CLIENTE_DESACTIVAR, id)));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), String.format("%s/%d", Uri.CLIENTE_DESACTIVAR, id)));
        }
    }
}
