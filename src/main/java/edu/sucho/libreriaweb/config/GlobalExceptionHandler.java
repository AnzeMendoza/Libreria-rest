package edu.sucho.libreriaweb.config;

import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler({ExceptionBadRequest.class})
    public ResponseEntity<?> BadRequestException(HttpServletRequest request , Exception e){
        ResponseInfo errorInfo = new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);
    }
}
