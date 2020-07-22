package com.exalt.villa.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
public class AddressMapper {
    private String country;
    private String city;
    private String street;
    private String number;
    private String postalCode;
    private GeoJsonPoint location;
}
