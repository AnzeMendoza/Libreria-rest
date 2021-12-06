package edu.sucho.libreriaweb.util;

import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.entity.Editorial;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static void ValidarParametros(BindingResult result) throws ExceptionBadRequest {
            if (result.hasErrors()) {
                List<ObjectError> oEs = result.getAllErrors().stream().collect(Collectors.toList());
                String err = "";
                for (ObjectError oE : oEs) {
                    FieldError fieldError = (FieldError) oE;
                    err += fieldError.getField() + " : " + fieldError.getDefaultMessage();
                }
                throw new ExceptionBadRequest(err);
            }
    }

    public static int getResponseId(String response) throws ExceptionBadRequest {
        try{
            return Integer.parseInt(response.split(",")[1]);
        } catch (NumberFormatException nfe){
            throw new ExceptionBadRequest("Debe ingresar una cadena con un numero");
        }
    }

    private static boolean getBoolean(int valor) {
        return (valor==1);
    }

    public static List<Editorial> getEditoriales(Connection conexion, String query) throws SQLException {
        ResultSet rs = Conexion.getResultSet(conexion,query);
        List<Editorial> editoriales= new ArrayList<>();
        while (Conexion.existeNext(rs))
        {
            Editorial editorial = new Editorial();
            editorial.setId(rs.getInt("id"));
            editorial.setAlta(getBoolean(rs.getInt("alta")));
            editorial.setNombre(rs.getString("nombre"));
            editoriales.add(editorial);
        }
        return editoriales;
    }


    public static int getId(Connection conexion, String query) {
        ResultSet rs = Conexion.getResultSet(conexion,query);
        Conexion.existeNext(rs);
        int id =0;
        try {
          id= rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return id;
    }
}
