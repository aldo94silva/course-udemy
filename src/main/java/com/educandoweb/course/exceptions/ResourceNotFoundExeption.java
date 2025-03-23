package com.educandoweb.course.exceptions;

public class ResourceNotFoundExeption extends RuntimeException{

    private static final long serialVersionUID=1L;

    public ResourceNotFoundExeption(Object id){
        super("Resource not found. Id" + id);
    }
}
