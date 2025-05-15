package com.haw.srs.customerservice;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class MembershipMailNotSent extends Exception {

    MembershipMailNotSent(String recipient) {
        super(String.format("Could not send membership mail to %s.", recipient));

    }
}