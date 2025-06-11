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
public class HafenTest {
    @Autowired
    private HafenRepository hafenRepository;

    @BeforeEach
    void setUp() {
        this.hafenRepository.deleteAll();
        GPSLocation gpsLocation = new GPSLocation("52.5200", "13.4050");
        Hafen hafen = new Hafen(gpsLocation, "Hafen");

        hafenRepository.save(hafen);
    }

    @Test
    void getHafenByGPS(){
        assertThat(hafenRepository.findByGpsLocation(new GPSLocation("52.5200", "13.4050")).isPresent()).isTrue();
    }
}
