package com.haw.srs.customerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BuchungTest {
    @Autowired
    private BuchungRepository buchungRepository;
    private Buchung buchung;
    private Rechnung rechnung;
    Benutzer benutzer;

    @BeforeEach
    void setUp() {
        this.buchungRepository.deleteAll();

        GPSLocation gpsLocationS = new GPSLocation("52.5200", "13.4050");
        Hafen startHafen = new Hafen(gpsLocationS, "startHafen");

        GPSLocation gpsLocationZ = new GPSLocation("53.5200", "14.4050");
        Hafen zielHafen = new Hafen(gpsLocationZ, "zielHafen");

        Email mail = new Email("test.test@test.de");
        Address address = new Address("Strasse", 1, 12345, "Stadt");
        PhoneNumber number = new PhoneNumber("+49", "123", "456");

        benutzer = new Benutzer(mail, address, "Vorname", "Nachname", number);

        rechnung = new Rechnung(benutzer, 35.66);

        Faehrfahrt faehrfahrt1 = new Faehrfahrt(startHafen, zielHafen, rechnung, new Kabine(KabinenTyp.Normal_Balkon, 1, 12));
        Faehrfahrt faehrfahrt2 = new Faehrfahrt(startHafen, zielHafen, rechnung, new Kabine(KabinenTyp.Normal_Balkon, 1, 13));
        buchung = new Buchung(rechnung);
        buchung.addFahrtToFahrten(faehrfahrt1);
        buchung.addFahrtToFahrten(faehrfahrt2);
        buchungRepository.save(buchung);
    }
    @Test
    void findById(){
        assertThat(buchungRepository.findById(buchung.getId()).isPresent()).isTrue();
    }
}
