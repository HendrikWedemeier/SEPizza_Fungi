package com.haw.srs.customerservice;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Entity
public class Rechnung implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long preis;
    private String kundenMail;

    public Rechnung(String kundenMail, double preis){
        this.preis = getPreis(preis);
        this.kundenMail = kundenMail;
    }

    public Rechnung() {

    }

    public static Long getPreis(double preis){
        return (long) (preis * 100);
    }
}
