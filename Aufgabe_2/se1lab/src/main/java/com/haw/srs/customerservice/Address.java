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
    private Integer number;
    private Integer zip;
    private String city;
}
