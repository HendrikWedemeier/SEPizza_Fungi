package com.haw.srs.customerservice;

public class DuplicateFirstNameException extends Exception{
    String name;
    public DuplicateFirstNameException(String name){
        super(String.format("The first name %s already exists in the database", name));

        this.name = name;
    }
}
