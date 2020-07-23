package com.exalt.villa.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
public class AddressDto {
    private String country;
    private String city;
    private String street;
    private String number;
    private String postalCode;
    private GeoJsonPoint location;
}
