package com.haw.srs.customerservice;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Address {
    private String street;
    private Integer number; //String regex
    private Integer zip; //Auch String reges, siehe Phonenumber
    private String city;
}
