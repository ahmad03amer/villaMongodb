package com.exalt.villa.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class VillaDto implements Serializable {
    private String id;
    private String name;
    private double cost;
    private AddressDto addressDto;
}
