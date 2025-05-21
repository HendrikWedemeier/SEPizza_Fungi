package com.haw.srs.customerservice;

public class ParticipantNotFoundException extends Exception{
    Long id;

    ParticipantNotFoundException(Long id){
        super(String.format("The participant with id %s doesn't exist", id));

        this.id = id;
    }
}
