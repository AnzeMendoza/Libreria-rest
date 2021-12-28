package edu.sucho.libreriaweb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

    private static String bd = "libreria_rest";
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String user = System.getenv().get("USUARIO");
    private static String password = System.getenv().get("CONTRASENA");
    private static String driver = "com.mysql.cj.jdbc.Driver";
    public static Connection conect() {

        Connection conexion = null;
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url + bd, user, password);
            System.out.println("se conecto a la base de datos");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(" no se conecto a la base de datos");
        }
        return conexion;
    }

    public static void disconect(Connection conexion) {
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getResultSet(Connection conexion, String query) {
        ResultSet resultSet = null;
        try {
            resultSet = conexion.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static Boolean existeNext(ResultSet rs) {
        Boolean next = true;
        try {
            next = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return next;
    }

}