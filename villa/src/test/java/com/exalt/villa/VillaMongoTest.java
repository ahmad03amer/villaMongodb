/*
package com.exalt.villa;

import com.exalt.villa.model.Address;
import com.exalt.villa.model.Villa;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;

@IfProfileValue(name = ACTIVE_PROFILES_PROPERTY_NAME, value = "it-embedded")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class VillaMongoTest {

    @LocalServerPort
    private int localPort;
    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    public Villa createVilla(){
        Villa villa = new Villa();
        villa.setName("lions");
        Address address= new Address();
        address.setCountry("palestine");
        address.setCity("jerusalem");
        address.setNumber("253");
        address.setPostalCode("DD256");
        address.setStreet("main street.");
        GeoJsonPoint location =new GeoJsonPoint(4, 15);
        address.setLocation(location);

        villa.setCost(2500);
        villa.setAddress(address);
        return villa;
    }

    @Test
    @Order(1)
    public void addVillaTest(){
        String addVillaUrl = "http://localhost:"+localPort+"/api/villa/";
        restTemplate = new RestTemplate();
        Villa villa = createVilla();
        villa.setId("1");

        HttpEntity<Villa> request = new HttpEntity<>(villa);
        ResponseEntity<Villa> result = restTemplate.postForEntity(addVillaUrl, request, Villa.class);
        if (villa != null){
            assertAll(
                    () -> assertEquals("1",villa.getId()),
                    () -> assertEquals("lions",villa.getName()),
                    () -> assertEquals("palestine",villa.getAddress().getCountry()),
                    () -> assertEquals("jerusalem",villa.getAddress().getCity()),
                    () -> assertEquals("253",villa.getAddress().getNumber()),
                    () -> assertEquals("main street.",villa.getAddress().getStreet()),
                    () -> assertEquals("DD256",villa.getAddress().getPostalCode())
            );
        }
        Assert.hasText("200 OK",result.getStatusCode().toString());
    }

    @Test
    @Order(2)
    public void getVillasTest(){
        String getVillaUrl = "http://localhost:"+localPort+"/api/villa/";
        restTemplate = new RestTemplate();

        List<Villa> villas = (List<Villa>) restTemplate.getForObject(getVillaUrl, Villa.class);
        assertNotNull(villas,"villas Array is null!!");
    }
}
*/
