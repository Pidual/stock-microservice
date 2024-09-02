package com.emazon.stock_microservice.common;

public class Constants {

    private Constants() { //this for sonarlint
        throw new IllegalStateException("Utility class");
    }

    public static final String WRONG_NAME_ERROR = "El nombre de marca no debe contener mas de 50 caracteres, estar vacio, o ser nullo";
    public static final String WRONG_DESCRIPTION_ERROR = "La descripcion de la marca no debe contener mas de 120 caracteres, estar vacio o ser nullo";
    public static final String ALREADY_EXITS_ERROR = "La marca ya existe";
    public static final String NOT_FOUND_ERROR = "No encontrada";






}
