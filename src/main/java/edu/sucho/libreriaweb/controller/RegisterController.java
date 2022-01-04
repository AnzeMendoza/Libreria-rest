package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.entity.Cliente;
import edu.sucho.libreriaweb.service.impl.inter.ClienteService;
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
@RequestMapping(path = Uri.REGISTER, produces = MediaType.APPLICATION_JSON_VALUE)

public class RegisterController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping ()
    public ResponseEntity<?> userRegister (@RequestBody @Valid Cliente cliente, BindingResult result) throws ExceptionBadRequest {

        try{
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));

        }catch(ExceptionBBDD e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Uri.REGISTER));
        }

    }
}
