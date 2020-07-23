package com.exalt.villa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("villaEn")
public class Villa {
    @Id
    private String id;
    private String name;
    private double cost;
    private Address address;
}
