package com.haw.srs.customerservice;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Buchung implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Faehrfahrt> fahrten;
    private Long rechnungId;

    public Buchung(Rechnung rechnung){
        this.fahrten = new ArrayList<>();
        this.rechnungId = rechnung.getId();
    }

    public void addFahrtToFahrten(Faehrfahrt fahrt){
        if(fahrt == null)
            throw new IllegalArgumentException("fahrt darf nicht null sein");
        this.fahrten.add(fahrt);
    }
}

