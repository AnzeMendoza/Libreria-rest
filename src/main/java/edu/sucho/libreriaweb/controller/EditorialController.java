package edu.sucho.libreriaweb.controller;

import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.dto.EditorialDTORequest;
import edu.sucho.libreriaweb.model.mapper.ModelMapperDTO;
import edu.sucho.libreriaweb.service.inter.EditorialService;
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
@RequestMapping(path = Uri.EDITORIAL, produces = MediaType.APPLICATION_JSON_VALUE)

public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @Autowired
    private ModelMapperDTO modelMapperDTO;

    @GetMapping("/")
    public ResponseEntity<?> getAll() throws ExceptionBBDD {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(modelMapperDTO.listEditorialToDto(editorialService.findAllByAlta()));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Uri.EDITORIAL));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(modelMapperDTO.editorialToDto(editorialService.findById(id)));
        } catch (ExceptionBBDD | ExceptionBadRequest e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), String.format("%s/%d", Uri.EDITORIAL, id)));
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody EditorialDTORequest editorialDTORequest, BindingResult result) throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(modelMapperDTO.editorialToDto(editorialService.save(editorialDTORequest)));
        } catch (ExceptionBBDD | ExceptionBadRequest ebd) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), ebd.getMessage(), Uri.EDITORIAL));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody EditorialDTORequest editorialDTORequest, BindingResult result) throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);
            return ResponseEntity.status(HttpStatus.OK).body(modelMapperDTO.editorialToDto(editorialService.update(id, editorialDTORequest)));
        } catch (ExceptionBBDD ebd) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), ebd.getMessage(), String.format("%s/%d", Uri.EDITORIAL, id)));
        }
    }

    @GetMapping(Uri.ACTIVAR + "/{id}")
    public ResponseEntity<?> active(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseInfo(HttpStatus.OK.value(), editorialService.changeStatus(id, Boolean.TRUE), Uri.EDITORIAL_ACTIVAR.concat("/") + id));
        } catch (ExceptionBBDD ebd) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), ebd.getMessage(), String.format("%s/%d", Uri.EDITORIAL_ACTIVAR, id)));
        }
    }

    @GetMapping(Uri.DESACTIVAR + "/{id}")
    public ResponseEntity<?> desactive(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseInfo(HttpStatus.OK.value(), editorialService.changeStatus(id, Boolean.FALSE), String.format("%s/%d", Uri.EDITORIAL_DESACTIVAR, id)));
        } catch (ExceptionBBDD ebd) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseInfo(HttpStatus.BAD_REQUEST.value(), ebd.getMessage(), String.format("%s/%d", Uri.EDITORIAL_DESACTIVAR, id)));
        }
    }
}
