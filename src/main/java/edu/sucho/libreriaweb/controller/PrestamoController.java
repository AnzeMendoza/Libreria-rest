package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.dto.PrestamoDTORequest;
import edu.sucho.libreriaweb.model.entity.Prestamo;
import edu.sucho.libreriaweb.model.mapper.ModelMapperDTO;
import edu.sucho.libreriaweb.service.inter.PrestamoService;
import edu.sucho.libreriaweb.util.Uri;
import edu.sucho.libreriaweb.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Uri.PRESTAMO, produces = MediaType.APPLICATION_JSON_VALUE)
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private ModelMapperDTO modelMapperDTO;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperDTO.listPrestamoToDto(prestamoService.findAll()));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Uri.PRESTAMO));
        }
    }

    // Todo filtrar por rol
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN') OR hasRole('ROLE_CLIENTE')")
    public ResponseEntity<?> getOne(@PathVariable("id") int id, HttpServletRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperDTO.prestamoToDto(prestamoService.findById(id)));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Uri.PRESTAMO + "/" + id));
        }
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody PrestamoDTORequest prestamoDTORequest, BindingResult result)
            throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(modelMapperDTO.prestamoToDto(prestamoService.save(prestamoDTORequest)));
        } catch (ExceptionBBDD | ExceptionBadRequest e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Uri.PRESTAMO));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@PathVariable("id") int id,
                                    @Valid @RequestBody PrestamoDTORequest prestamoDTORequest,
                                    BindingResult result)
            throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modelMapperDTO.prestamoToDto(prestamoService.update(id, prestamoDTORequest)));
        } catch (ExceptionBBDD | ExceptionBadRequest e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Uri.PRESTAMO + "/"+id));
        }
    }

    @GetMapping("desactivar/{id}")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deactivate(@PathVariable("id") int id)
            throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseInfo(HttpStatus.OK.value(), prestamoService.disableStatus(id), Uri.PRESTAMO_DESACTIVAR + "/" + id));
        } catch (ExceptionBBDD ebd) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), ebd.getMessage(), Uri.PRESTAMO_DESACTIVAR + "/" + id));
        }
    }

    @GetMapping("activar/{id}")
    @PreAuthorize("hasRole('ROLE_PERSONAL') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> activar(@PathVariable("id") int id)
            throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseInfo(HttpStatus.OK.value(), prestamoService.enableStatus(id), Uri.PRESTAMO_ACTIVAR + "/" + id));
        } catch (ExceptionBBDD ebd) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), ebd.getMessage(), Uri.PRESTAMO_ACTIVAR + "/"+id));
        }
    }
}
