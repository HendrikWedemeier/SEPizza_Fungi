package com.haw.srs.customerservice;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Entity
public class Hafen implements Serializable {
    @EmbeddedId
    private GPSLocation gpsLocation;
    private String name;

    public Hafen() {

    }
}
