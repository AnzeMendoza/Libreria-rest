package edu.sucho.libreriaweb.util;

import edu.sucho.libreriaweb.model.Editorial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static edu.sucho.libreriaweb.util.Util.getBoolean;

public  class Conexion {
/*    private static String bd ="libreria_rest";
    private  static String url = "jdbc:mysql://localhost:3306/";
    private static String user ="root";
    private static String password="root";
     private static String driver ="com.mysql.cj.jdbc.Driver";

    public static Connection conect() {
        Connection conexion = null;
        try {
            Class.forName(driver);
             conexion = DriverManager.getConnection(url+bd,user,password);
            System.out.println("se conecto a la base de datos");
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(" no se conecto a la base de datos");
        }
        return conexion;
    }
    public static void disconect(Connection conexion)  {
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ResultSet getResultSet(Connection conexion, String query){
        ResultSet resultSet = null;
        try {
            resultSet = conexion.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public static Boolean existeNext(ResultSet rs){
        Boolean next = true;
        try {
         next = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return next;
    }

*//*
   public static void main(String[] args) throws SQLException {
       Connection conexion =Conexion.conect();
       List<Editorial> editoriales = Util.getEditoriales(conexion,"select * from editorial");
        editoriales.forEach(editorial -> {
            System.out.println("editorial.toString() = " + editorial.toString());
        });
        *//**//*
        Connection conexion =Conexion.conect();
        ResultSet rs = Conexion.getResultSet(conexion,"select * from editorial");
        List<Editorial> editoriales= new ArrayList<>();

        while (Conexion.existeNext(rs))
        {
            Editorial editorial = new Editorial();
            editorial.setId(rs.getInt("id"));
            editorial.setAlta(getBoolean(rs.getInt("alta")));
            editorial.setNombre(rs.getString("nombre"));
            editoriales.add(editorial);

           //System.out.println(rs.getInt("id")+" "+rs.getInt("alta")+" "+rs.getString("nombre"));
        }*//**//*
    }*/


}