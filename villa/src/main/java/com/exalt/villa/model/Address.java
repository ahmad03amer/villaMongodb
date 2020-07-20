package com.exalt.villa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @TextIndexed(weight = 9)
    private String country;
    @TextIndexed(weight = 3)
    private String city;
    @TextIndexed
    private String street;
    private String number;
    @Field(name = "postal_code")
    private String postalCode;
    private double []position;

}
