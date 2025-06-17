package com.haw.srs.customerservice;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Getter
@Data
@AllArgsConstructor
@Embeddable
public class Address {
    private String street;
    private Integer number; //String regex
    private Integer zip; //Auch String reges, siehe Phonenumber
    private String city;

    public Address() {

    }
}
