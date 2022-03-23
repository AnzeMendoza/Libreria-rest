package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.dto.PrestamoRequestDTO;
import edu.sucho.libreriaweb.model.dto.PrestamoResponseDTO;
import edu.sucho.libreriaweb.model.entity.Prestamo;
import edu.sucho.libreriaweb.model.mapper.ModelMapperDTO;
import edu.sucho.libreriaweb.service.inter.PrestamoService;
import edu.sucho.libreriaweb.util.Uri;
import edu.sucho.libreriaweb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Uri.PRESTAMO, produces = MediaType.APPLICATION_JSON_VALUE)
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private ModelMapperDTO modelMapperDTO;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            return responsePrestamos(prestamoService.findAll());
        } catch (ExceptionBBDD ebd) {
            return responseExceptionBBDD(ebd, Uri.PRESTAMO);
        }
    }

    @GetMapping("/paged")
    public ResponseEntity<?> getAll(Pageable pageable) {
        try {
            Page<Prestamo> entities =  prestamoService.findAll(pageable);
            Page<PrestamoResponseDTO> dtoPage = entities.map(prestamo -> modelMapperDTO.prestamoToDto(prestamo));
            return ResponseEntity.ok().body(dtoPage);
        } catch (ExceptionBBDD e) {
            return responseExceptionBadRequest(e, Uri.PRESTAMO);
        }
    }

    @GetMapping("/{id}/"+Uri.GETALL)
    public ResponseEntity<?> getAllCliente(@PathVariable("id") int id) {
        try {
            return responsePrestamos(prestamoService.prestamosPorIdCliente(id));
        } catch (ExceptionBBDD ebd) {
            return responseExceptionBBDD(ebd, Uri.PRESTAMO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") int id) {
        try {
            return responsePrestamo(id);
        } catch (ExceptionBBDD | ExceptionBadRequest ebd) {
            return responseExceptionBBDD(ebd, Uri.PRESTAMO + "/" + id);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody PrestamoRequestDTO prestamo, BindingResult result)
            throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return responseSavePrestamo(prestamo);
        } catch (ExceptionBBDD | ExceptionBadRequest ebd) {
            return responseExceptionBBDD(ebd, Uri.PRESTAMO);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id,
                                    @Valid @RequestBody PrestamoRequestDTO prestamo,
                                    BindingResult result)
            throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return responsePrestamoUpdate(id, prestamo);
        } catch (ExceptionBBDD | ExceptionBadRequest ebd) {
            return responseExceptionBBDD(ebd, Uri.PRESTAMO + "/" + id);
        }
    }

    @GetMapping("desactivar/{id}")
    public ResponseEntity<?> deactivate(@PathVariable("id") int id){
        try {
            return responsePrestamoDisable(id);
        } catch (ExceptionBBDD ebd) {
            return responseExceptionBBDD(ebd, Uri.PRESTAMO_DESACTIVAR + "/" + id);
        }
    }

    @GetMapping("activar/{id}")
    public ResponseEntity<?> activar(@PathVariable("id") int id){
             try {
            return responsePrestamoEnable(id);
        } catch (ExceptionBBDD ebd) {
            return responseExceptionBBDD(ebd, Uri.PRESTAMO_ACTIVAR + "/" + id);
        }
    }

    private ResponseEntity<?> responsePrestamos(List<Prestamo> prestamos) throws ExceptionBBDD {
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapperDTO.listPrestamoToDto(prestamos));
    }

    private ResponseEntity<?> responsePrestamo(int id) throws ExceptionBBDD, ExceptionBadRequest {
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapperDTO.prestamoToDto(prestamoService.findById(id)));
    }

    private ResponseEntity<?> responsePrestamoUpdate(int id, PrestamoRequestDTO prestamo) throws ExceptionBBDD, ExceptionBadRequest {
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapperDTO.prestamoToDto(prestamoService.update(id, prestamo)));
    }

    private ResponseEntity<?> responseSavePrestamo(PrestamoRequestDTO prestamo) throws ExceptionBBDD, ExceptionBadRequest {
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapperDTO.prestamoToDto(prestamoService.save(prestamo)));
    }

    private ResponseEntity<?> responsePrestamoEnable(int id) throws ExceptionBBDD {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseInfo(HttpStatus.OK.value(), prestamoService.enableStatus(id), Uri.PRESTAMO_ACTIVAR + "/" + id));
    }

    private ResponseEntity<?> responsePrestamoDisable(int id) throws ExceptionBBDD {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseInfo(HttpStatus.OK.value(), prestamoService.disableStatus(id), Uri.PRESTAMO_DESACTIVAR + "/" + id));
    }

    private ResponseEntity<?> responseExceptionBBDD(Exception exception, String path) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), path));
    }

    private ResponseEntity<?> responseExceptionBadRequest(Exception exception, String path) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), path));
    }
}
