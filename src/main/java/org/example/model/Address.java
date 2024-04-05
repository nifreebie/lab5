package org.example.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Address {
    @JacksonXmlProperty(localName = "street")
    private String street;


    public Address(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                '}';
    }
}
