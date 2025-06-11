package com.haw.srs.customerservice;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Getter
@Embeddable
public class GPSLocation implements Serializable {
    private String lat;
    private String lon;

    public GPSLocation(String lat, String lon) {
        //source chatGPT
        String latRegex = "[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?)";
        String lonRegex = "[-+]?((1[0-7]\\d|\\d{1,2})(\\.\\d+)?|180(\\.0+)?)";
        boolean isValidLat = Pattern.matches(latRegex, lat);
        boolean isValidLon = Pattern.matches(lonRegex, lon);
        Pattern latPattern = Pattern.compile(latRegex);
        Matcher latMatcher = latPattern.matcher(lat);
        if (!latMatcher.matches()) {
            throw new IllegalArgumentException("Invalid phone number: " + lat);
        }

        Pattern lonPattern = Pattern.compile(lonRegex);
        Matcher lonMatcher = lonPattern.matcher(lon);
        if (!lonMatcher.matches()) {
            throw new IllegalArgumentException("Invalid phone number: " + lon);
        }

        this.lat = lat;
        this.lon = lon;
    }

    public GPSLocation() {

    }
}
