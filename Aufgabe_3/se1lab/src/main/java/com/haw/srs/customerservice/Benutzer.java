package com.haw.srs.customerservice;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Benutzer implements Serializable {
    @EmbeddedId
    private Email email;
    private Address address;
    private String vorname;
    private String nachname;
    private PhoneNumber telefon;

    public Benutzer(Email email, Address address, String vorname, String nachname, PhoneNumber number){
        this.email = email;
        this.address = address;
        this.vorname = vorname;
        this.nachname = nachname;
        this.telefon = number;
    }

    public Benutzer() {

    }
}
