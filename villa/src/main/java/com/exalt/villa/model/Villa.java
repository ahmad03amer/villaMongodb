package com.exalt.villa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("villaEntity")
public class Villa {

    @Id
    private String id;
    @TextIndexed(weight = 5)
    private String name;
    //@TextScore
    private double cost;
    private Address address;

}
