package edu.sucho.libreriaweb.util;

import edu.sucho.libreriaweb.exception.ExceptionBadRequest;
import edu.sucho.libreriaweb.model.entity.Autor;
import edu.sucho.libreriaweb.model.entity.Cliente;
import edu.sucho.libreriaweb.model.entity.Editorial;
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
        try {
            return Integer.parseInt(response.split(",")[1]);
        } catch (NumberFormatException nfe) {
            throw new ExceptionBadRequest("Debe ingresar una cadena con un numero");
        }
    }

    private static boolean getBoolean(int valor) {
        return (valor == 1);
    }

    public static List<Editorial> getEditoriales(Connection conexion, String query) throws SQLException {
        ResultSet rs = Conexion.getResultSet(conexion, query);
        List<Editorial> editoriales = new ArrayList<>();
        while (Conexion.existeNext(rs)) {
            Editorial editorial = new Editorial();
            editorial.setId(rs.getInt("id"));
            editorial.setAlta(getBoolean(rs.getInt("alta")));
            editorial.setNombre(rs.getString("nombre"));
            editoriales.add(editorial);
        }

        return editoriales;
    }
    
    public static List<Autor> getAutores(Connection conexion, String query) throws SQLException {
        ResultSet rs = Conexion.getResultSet(conexion,query);
        List<Autor> autores= new ArrayList<>();
        while (Conexion.existeNext(rs))
        {
            Autor autor = new Autor();
            autor.setId(rs.getInt("id"));
            autor.setAlta(getBoolean(rs.getInt("alta")));
            autor.setNombre(rs.getString("nombre"));
            autores.add(autor);
        }
        return autores;
    }

    public static List<Prestamo> getPrestamo(Connection conexion, String query) throws SQLException {
        ResultSet rs = Conexion.getResultSet(conexion,query);
        List<Prestamo> prestamos= new ArrayList<>();

        while (Conexion.existeNext(rs)) {
            Prestamo prestamo = new Prestamo();
            prestamo.setId(rs.getInt("id"));
            prestamo.setAlta(getBoolean(rs.getInt("alta")));
            prestamo.setFechaPrestamo(dateToCalendar( rs.getDate("fecha_prestamo")));
            prestamo.setFechaDevolucion(dateToCalendar(rs.getDate("fecha_devolucion")));
            prestamos.add(prestamo);
        }
        return prestamos;
    }

    private static Calendar dateToCalendar(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date addDays(Date fecha, int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    public static void getCorrectTime(){
    public static List<Cliente> getClientes(Connection conexion, String query) throws SQLException {
        ResultSet rs = Conexion.getResultSet(conexion, query);
        List<Cliente> clientes = new ArrayList<>();
        while (Conexion.existeNext(rs)) {
            Cliente cliente = new Cliente();

            cliente.setId(rs.getInt("id"));
            cliente.setAlta(getBoolean(rs.getInt("alta")));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setApellido(rs.getString("apellido"));
            cliente.setTelefono(rs.getString("telefono"));
            cliente.setDocumento(rs.getLong("documento"));
            clientes.add(cliente);
        }
        return clientes;
    }

    public static void getCorrectTime() {
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
