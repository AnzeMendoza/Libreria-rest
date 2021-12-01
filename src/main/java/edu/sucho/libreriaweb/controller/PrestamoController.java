
package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.Prestamo;
import edu.sucho.libreriaweb.service.PrestamoService;
import edu.sucho.libreriaweb.util.Uri;
import edu.sucho.libreriaweb.util.Util;
import javax.validation.Valid;

//import netscape.javascript.JSObject;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Uri.PRESTAMO, produces = MediaType.APPLICATION_JSON_VALUE)
public class PrestamoController {
    @Autowired
    private PrestamoService prestamoService;
    
    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Prestamo prestamo, BindingResult result) throws ExceptionBadRequest {
        try {
            //prestamo.getFechaDevolucion().setTimeZone(TimeZone.getTimeZone("America/Buenos_Aires"));
            System.out.println(">>>>>>>>>>>>>>>>>>>>> fecha actual: " + prestamo.getFechaDevolucion().getTime());
            ZonedDateTime fecha = ZonedDateTime.of(prestamo.getFechaDevolucion().get(Calendar.YEAR),
                    prestamo.getFechaDevolucion().get(Calendar.MONTH) + 1,
                    prestamo.getFechaDevolucion().get(Calendar.DAY_OF_MONTH),
                    prestamo.getFechaDevolucion().get(Calendar.HOUR_OF_DAY),
                    prestamo.getFechaDevolucion().get(Calendar.MINUTE),
                    prestamo.getFechaDevolucion().get(Calendar.SECOND),
                    prestamo.getFechaDevolucion().get(Calendar.MILLISECOND),
                    ZoneId.of("America/Argentina/Buenos_Aires"));

            prestamo.getFechaDevolucion().set(prestamo.getFechaDevolucion().get(Calendar.YEAR),
                    prestamo.getFechaDevolucion().get(Calendar.MONTH),
                    prestamo.getFechaDevolucion().get(Calendar.DAY_OF_MONTH));
            prestamo.getFechaPrestamo().set(prestamo.getFechaPrestamo().get(Calendar.YEAR),
                    prestamo.getFechaPrestamo().get(Calendar.MONTH),
                    prestamo.getFechaPrestamo().get(Calendar.DAY_OF_MONTH));

            prestamo.getFechaDevolucion().setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
            prestamo.getFechaPrestamo().setTimeZone(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));

            System.out.println("-------------------");
            System.out.println("Fecha de devolucion: " + prestamo.getFechaDevolucion().getTime());
            System.out.println("Fecha de prestamo: " + prestamo.getFechaPrestamo().getTime());
            System.out.println("-------------------");

            Util.ValidarParametros(result);
          return ResponseEntity.status(HttpStatus.CREATED)
                  .body(prestamoService.save(prestamo));
         }
        catch (ExceptionBBDD | ExceptionBadRequest e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(),e.getMessage(),Uri.PRESTAMO));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, 
            @Valid @RequestBody Prestamo prestamo,BindingResult result ) throws ExceptionBadRequest {
         try {
             Util.ValidarParametros(result);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(prestamoService.update(id,prestamo));

        } catch (ExceptionBBDD | ExceptionBadRequest e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(),e.getMessage(),Uri.PRESTAMO+"/"+id));
        }
    }
    
}
