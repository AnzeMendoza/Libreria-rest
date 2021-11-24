package edu.sucho.libreriaweb.controller;

import dto.LibroDTO;
import edu.sucho.libreriaweb.config.ResponseInfo;
import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.Libro;
import edu.sucho.libreriaweb.service.LibroService;
import edu.sucho.libreriaweb.util.Uri;
import edu.sucho.libreriaweb.util.Util;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/libro", produces = MediaType.APPLICATION_JSON_VALUE)
public class LibroController {

    @Autowired
    private LibroService libroService;
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    @Autowired
    private ModelMapper modelMapper;
    
    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        try {
            List<Libro> libros = libroService.findAll();
            
        List<LibroDTO> librosDto = libros.stream()
          .map(this::convertToDto)
          .collect(Collectors.toList());
             
            return ResponseEntity.status(HttpStatus.OK).body(librosDto);
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {
            Libro libro = libroService.findById(id);
            
            String nombreAutor =libro.getAutor().getNombre();
            
            LibroDTO libroDto = new LibroDTO();
            
            //libroDto.setNombreAutor(nombreAutor);
            
            libroDto = convertToDto(libro);
            libroDto.setAnio(2080);
            libroDto.setAutor(nombreAutor);
            
            return ResponseEntity.status(HttpStatus.OK).body(libroDto);
        } catch (ExceptionBBDD e) {
            throw new ExceptionBadRequest(e.getMessage());
        } catch(Exception e){
            throw new ExceptionBadRequest("No existe ese libro");
        }

    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Libro libro, BindingResult result) throws ExceptionBadRequest {
        try {
            if (result.hasErrors()) { // Hay un error
                List<ObjectError> oEs = result.getAllErrors().stream().collect(Collectors.toList());
                String err = "";
                for (ObjectError oE : oEs) {
                    FieldError fieldError = (FieldError) oE;
                    err += fieldError.getField() + " : " + fieldError.getDefaultMessage();

                }
                throw new ExceptionBadRequest(err);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(libroService.save(libro));
        } catch (ExceptionBadRequest ex) {
            throw new ExceptionBadRequest(ex.getMessage());
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody Libro libro, BindingResult result) throws ExceptionBadRequest {
        try {
            Util.ValidarParametros(result);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(libroService.update(id, libro));

        } catch (ExceptionBBDD | ExceptionBadRequest ebd) {
            throw new ExceptionBadRequest(ebd.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(libroService.delete(id));
        } catch (ExceptionBBDD e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\" : \"error\"}");
        }
    }

    @GetMapping("desactivar/{id}")
    private ResponseEntity<?> deactivate(@PathVariable("id") int id) throws ExceptionBadRequest {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseInfo(HttpStatus.OK.value(), libroService.disableStatus(id), Uri.LIBRO_DESACTIVAR));
        } catch (ExceptionBBDD ebd) {
            throw new ExceptionBadRequest(ebd.getMessage());
        }
    }
    
    private LibroDTO convertToDto(Libro libro) {
    LibroDTO libroDto = modelMapper.map(libro, LibroDTO.class);
    return libroDto;
}
}
