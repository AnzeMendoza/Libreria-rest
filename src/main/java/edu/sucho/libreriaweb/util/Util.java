package edu.sucho.libreriaweb.util;

import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.entity.Cliente;
import edu.sucho.libreriaweb.model.entity.Editorial;
import edu.sucho.libreriaweb.model.entity.Libro;
import edu.sucho.libreriaweb.model.entity.Prestamo;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

        while (Conexion.existeNext(rs)) {
            Editorial editorial = new Editorial();
            editorial.setId(rs.getInt("id"));
            editorial.setAlta(getBoolean(rs.getInt("alta")));
            editorial.setNombre(rs.getString("nombre"));
            editoriales.add(editorial);
        }

        return editoriales;
    }

    public static List<Prestamo> getPrestamo(Connection conexion, String query) throws SQLException {
        ResultSet rs = Conexion.getResultSet(conexion,query);
        List<Prestamo> prestamos= new ArrayList<>();

        while (Conexion.existeNext(rs)) {
            Prestamo prestamo = new Prestamo();
            prestamo.setId(rs.getInt("id"));
            prestamo.setAlta(getBoolean(rs.getInt("alta")));
//            prestamo.setFechaPrestamo(Calendar.getInstance().getTime(rs.getDate("fechaPrestamo")));
            prestamos.add(prestamo);
        }
        return prestamos;
    }

    public static void getCorrectTime(){
        /*prestamo.getFechaDevolucion().set(prestamo.getFechaDevolucion().get(Calendar.YEAR),
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
            System.out.println("-------------------");*/
    }
}
