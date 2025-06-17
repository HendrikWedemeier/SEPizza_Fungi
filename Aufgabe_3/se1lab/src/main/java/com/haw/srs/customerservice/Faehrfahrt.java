package com.haw.srs.customerservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
public class Faehrfahrt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Hafen startHafen;
    private Hafen zielHafen;
    private Kabine kabine;

    public Faehrfahrt(Hafen startHafen, Hafen zielHafen, Rechnung rechnung, Kabine kabine){
        this.startHafen = startHafen;
        this.zielHafen = zielHafen;
        this.kabine = kabine;
    }

    public Faehrfahrt() {

    }
}
