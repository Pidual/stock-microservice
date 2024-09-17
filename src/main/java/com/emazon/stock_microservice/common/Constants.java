package com.emazon.stock_microservice.common;

public class Constants {

    private Constants() { //this for sonarlint
        throw new IllegalStateException("Utility class");
    }

    public static final String WRONG_NAME_ERROR = "El nombre de marca no debe contener mas de 50 caracteres, estar vacio, o ser nullo";
    public static final String WRONG_DESCRIPTION_ERROR = "La descripcion de la marca no debe contener mas de 120 caracteres, estar vacio o ser nullo";
    public static final String BRAND_ALREADY_EXITS_ERROR = "La marca ya existe";
    public static final String NOT_FOUND_ERROR = "No encontrada";
    public static final String ARTICLE_NOT_FOUND_ERROR_TEXT = "Articulo no encontrado con el nombre: ";
    public static final String ARTICLE_ALREADY_EXISTS_ERROR =  "Articulo ya existe";
    public static final String ARTICLE_STOCK_MUST_BE_POSITIVE ="La cantidad adicional de stock debe ser mayor que 0";




}
