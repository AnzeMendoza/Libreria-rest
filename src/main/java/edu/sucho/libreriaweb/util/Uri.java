package edu.sucho.libreriaweb.util;

public class Uri {

    public static final String URI = "/api/v1";
    public final static String ACTIVAR = "/activar";
    public final static String DESACTIVAR = "/desactivar";

    public static final String AUTOR = URI + "/autor";
    public static final String AUTOR_ACTIVAR = AUTOR + ACTIVAR;
    public static final String AUTOR_DESACTIVAR = AUTOR + DESACTIVAR;

    public static final String LIBRO = URI + "/libro";
    public static final String LIBRO_ACTIVAR = LIBRO + ACTIVAR;
    public static final String LIBRO_DESACTIVAR = LIBRO + DESACTIVAR;

    public static final String CLIENTE = URI + "/cliente";
    public static final String CLIENTE_ACTIVAR = CLIENTE + ACTIVAR;
    public static final String CLIENTE_DESACTIVAR = CLIENTE + DESACTIVAR;

    public static final String EDITORIAL = URI + "/editorial";
    public static final String EDITORIAL_ACTIVAR = EDITORIAL + ACTIVAR;
    public static final String EDITORIAL_DESACTIVAR = EDITORIAL + DESACTIVAR;

    public static final String PRESTAMO = URI + "/prestamo";
    public static final String GETALL = "/getall";
    public static final String PRESTAMO_ACTIVAR = PRESTAMO + ACTIVAR;
    public static final String PRESTAMO_DESACTIVAR = PRESTAMO + DESACTIVAR;

}
