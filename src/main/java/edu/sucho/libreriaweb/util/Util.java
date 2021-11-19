package edu.sucho.libreriaweb.util;

import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static void ValidarParametros(BindingResult result) throws ExceptionBadRequest {

            if (result.hasErrors()) { // Hay un error
                List<ObjectError> oEs = result.getAllErrors().stream().collect(Collectors.toList());
                String err = "";
                for (ObjectError oE : oEs) {
                    FieldError fieldError = (FieldError) oE;
                    err += fieldError.getField() + " : " + fieldError.getDefaultMessage();
                }
                throw new ExceptionBadRequest(err);
            }

    }
}
