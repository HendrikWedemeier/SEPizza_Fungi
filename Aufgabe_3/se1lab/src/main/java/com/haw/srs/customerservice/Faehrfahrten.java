package com.haw.srs.customerservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
public class Faehrfahrten implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Hafen startHafen;
    private Hafen zielHafen;
    private Rechnung rechnung;
    private Kabine kabine;

    public Faehrfahrten(Hafen startHafen, Hafen zielHafen, Rechnung rechnung, Kabine kabine){
        this.startHafen = startHafen;
        this.zielHafen = zielHafen;
        this.rechnung = rechnung;
        this.kabine = kabine;
    }

    public Faehrfahrten() {

    }
}
