package com.haw.srs.customerservice;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private Integer number;
    private Integer zip;
    private String city;
}
