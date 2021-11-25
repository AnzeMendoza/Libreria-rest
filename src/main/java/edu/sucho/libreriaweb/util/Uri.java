package edu.sucho.libreriaweb.util;

public class Uri {

    private final static String ACTIVAR = "/activar";
    private final static String DESACTIVAR = "/desactivar";

    public static final String URI = "/api/v1";
    public static final String AUTOR = URI+"/autor";
    public static final String AUTOR_ACTIVAR = AUTOR+ACTIVAR;
    public static final String AUTOR_DESACTIVAR = AUTOR+DESACTIVAR;

    public static final String LIBRO = URI+"/libro";
    public static final String LIBRO_ACTIVAR = LIBRO+ACTIVAR;
    public static final String LIBRO_DESACTIVAR = LIBRO+DESACTIVAR;

    public static final String CLIENTE = URI+"/cliente";
    public static final String CLIENTE_ACTIVAR = CLIENTE+ACTIVAR;
    public static final String CLIENTE_DESACTIVAR = CLIENTE+DESACTIVAR;


}
