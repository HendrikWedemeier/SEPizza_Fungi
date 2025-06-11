package com.haw.srs.customerservice;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Data
@Embeddable
public class Kabine {
    private Integer nummer;
    @Enumerated
    private KabinenTyp typ;
    private Integer bettenZahl;

    public Kabine(KabinenTyp typ, Integer bettenZahl, Integer nummer){
        this.bettenZahl = bettenZahl;
        this.typ = typ;
        this.nummer = nummer;
    }

    public Kabine() {

    }
}
