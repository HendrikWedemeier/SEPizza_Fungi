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
public class RechnungTest {
    @Autowired
    private RechnungRepository rechnungRepository;
    Rechnung rechnung;

    @BeforeEach
    void setUp() {
        this.rechnungRepository.deleteAll();
        Email mail = new Email("test.test@test.de");
        Address address = new Address("Strasse", 1, 12345, "Stadt");
        PhoneNumber number = new PhoneNumber("+49", "123", "456");

        Benutzer benutzer = new Benutzer(mail, address, "Vorname", "Nachname", number);

        rechnung = new Rechnung(benutzer, 35.66);

        rechnungRepository.save(rechnung);
    }

    @Test
    void findById(){
        assertThat(rechnungRepository.findById(rechnung.getId()).isPresent()).isTrue();
    }
}
