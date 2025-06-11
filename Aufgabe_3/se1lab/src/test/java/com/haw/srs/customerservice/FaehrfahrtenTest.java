package com.haw.srs.customerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class FaehrfahrtenTest {
    @Autowired
    private FaehrfahrtenRepository faehrfahrtenRepository;
    private Faehrfahrten faehrfahrten;

    @BeforeEach
    void setUp() {
        this.faehrfahrtenRepository.deleteAll();

        GPSLocation gpsLocationS = new GPSLocation("52.5200", "13.4050");
        Hafen startHafen = new Hafen(gpsLocationS, "startHafen");

        GPSLocation gpsLocationZ = new GPSLocation("53.5200", "14.4050");
        Hafen zielHafen = new Hafen(gpsLocationZ, "zielHafen");

        Email mail = new Email("test.test@test.de");
        Address address = new Address("Strasse", 1, 12345, "Stadt");
        PhoneNumber number = new PhoneNumber("+49", "123", "456");

        Benutzer benutzer = new Benutzer(mail, address, "Vorname", "Nachname", number);

        Rechnung rechnung = new Rechnung(benutzer.getEmail().getAddress(), 35.66);

        faehrfahrten = new Faehrfahrten(startHafen, zielHafen, rechnung, new Kabine(KabinenTyp.Normal_Balkon, 1, 12));

        faehrfahrtenRepository.save(faehrfahrten);
    }

    @Test
    void findById(){
        assertThat(faehrfahrtenRepository.findById(faehrfahrten.getId()).isPresent()).isTrue();
    }
}
