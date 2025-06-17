package com.haw.srs.customerservice;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Data
@Embeddable
public class Email implements Serializable {
    //source ChatGPT
    private String address;

    public Email(String email) throws IllegalArgumentException{
        //source ChatGPT
        String emailRegex = "^[a-zA-Z0-9]+([._-]?[a-zA-Z0-9]+)*@[a-zA-Z0-9]+([.-]?[a-zA-Z0-9]+)*\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid phone number: " + email);
        }

        address = email;
    }

    public Email() {

    }
}
