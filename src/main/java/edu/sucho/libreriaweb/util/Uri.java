package edu.sucho.libreriaweb.util;

public class Uri {
    public static final String URI = "/api/v1";
    private final static String ACTIVAR = "/activar";
    private final static String DESACTIVAR = "/desactivar";

    public static final String AUTOR = URI+"/autor";
    public static final String AUTOR_ACTIVAR = AUTOR+ACTIVAR;
    public static final String AUTOR_DESACTIVAR = AUTOR+DESACTIVAR;

    public static final String LIBRO = URI+"/libro";
    public static final String LIBRO_ACTIVAR = LIBRO+ACTIVAR;
    public static final String LIBRO_DESACTIVAR = LIBRO+DESACTIVAR;

    public static final String EDITORIAL = URI+"/editorial";
    public static final String EDITORIAL_ACTIVAR = EDITORIAL+ACTIVAR;
    public static final String EDITORIAL_DESACTIVAR = EDITORIAL+DESACTIVAR;



}
