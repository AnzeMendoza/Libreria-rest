package edu.sucho.libreriaweb.config;

import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler({ExceptionBadRequest.class})
    public ResponseEntity<?> BadRequestException(HttpServletRequest request, ExceptionBadRequest e) {
        ResponseInfo errorInfo = new ResponseInfo(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<?> AccessDeniedException(HttpServletRequest request, Exception e) {
        ResponseInfo errorInfo = new ResponseInfo(HttpStatus.FORBIDDEN.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorInfo);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<?> MethodArgumentTypeException(HttpServletRequest request, NumberFormatException e) {
        ResponseInfo errorInfo = new ResponseInfo(HttpStatus.FORBIDDEN.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorInfo);
    }
}
