package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.model.dto.JwtDTO;
import edu.sucho.libreriaweb.model.dto.UserNotAutorizer;
import edu.sucho.libreriaweb.model.entity.Cliente;
import edu.sucho.libreriaweb.security.EncryptPassword;
import edu.sucho.libreriaweb.security.JWT;
import edu.sucho.libreriaweb.service.inter.ClienteService;
import edu.sucho.libreriaweb.util.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Uri.AUTH, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthUsernameController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EncryptPassword encryptPassword;

    @Autowired
    private JWT jwt;

    @Autowired
    private JwtDTO dato;

    @PostMapping(value = "/")
    public ResponseEntity<?> login(@RequestBody Cliente cliente) {
        String tokenJwt="";
        try {
            Cliente clienteLogueado = clienteService.findByUsername(cliente.getUsername());
            if(!encryptPassword.verificarIgualdadDeCadenas(clienteLogueado.getPassword(), cliente.getPassword())){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body( new UserNotAutorizer("access_denied","Unauthorized"));
            }
            tokenJwt = jwt.create(String.valueOf(clienteLogueado.getId()), clienteLogueado.getUsername());
        } catch (ExceptionBBDD e) {
            e.printStackTrace();
        }
        dato.setJwt(tokenJwt);
        return ResponseEntity.status(HttpStatus.OK).body( dato);
    }
}
