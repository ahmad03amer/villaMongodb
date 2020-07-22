package com.exalt.villa.dto;

import com.exalt.villa.model.Address;
import lombok.Data;

@Data
public class VillaMapper {
    private String id;
    private String name;
    private double cost;
    private Address address;
}
