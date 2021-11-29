package edu.sucho.libreriaweb.util;

import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.Editorial;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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

    public static String generarString(){
        return UUID.randomUUID().toString().replace("-", "").substring(0, 9);
    }

    /* sector base de datos */
    public static  int getIdMax(Connection conexion, String query)  {
        int indice = 0;
        try {
            ResultSet rs = Conexion.getResultSet(conexion,query);
            rs.next();
            indice =rs.getInt("max(id)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indice;
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
}
