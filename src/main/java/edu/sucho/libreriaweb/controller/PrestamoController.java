package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.Prestamo;
import edu.sucho.libreriaweb.service.PrestamoService;
import edu.sucho.libreriaweb.util.Uri;
import edu.sucho.libreriaweb.util.Util;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Uri.PRESTAMO, produces = MediaType.APPLICATION_JSON_VALUE)
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Prestamo prestamo, BindingResult result) throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            /*System.out.println("--------");
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
            isoFormat.setTimeZone(TimeZone.getTimeZone("UTC-3"));
            isoFormat.parse(prestamo.getFechaDevolucion());*/
            System.out.println(prestamo.getFechaDevolucion()/*.set.setTimeZone(TimeZone.getTimeZone("UTC-3"))*/);
            System.out.println(prestamo.getFechaPrestamo());
            System.out.println("--------");
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(prestamoService.save(prestamo));
        } catch (ExceptionBBDD | ExceptionBadRequest e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Uri.PRESTAMO));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id,
            @Valid @RequestBody Prestamo prestamo, BindingResult result) throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(prestamoService.update(id, prestamo));

        } catch (ExceptionBBDD | ExceptionBadRequest e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Uri.PRESTAMO + "/" + id));
        }
    }

}
