package com.exalt.villa;

import com.exalt.villa.model.Address;
import com.exalt.villa.model.Villa;
import com.exalt.villa.repository.VillaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VillaApplicationTests {

	@Autowired
	private VillaRepository villaRepository;
	@Autowired
	private MongoTemplate template;
	@Test
	void contextLoads() {
	}

	public Villa createEmployee(){
		Villa villa = new Villa();
		villa.setName("lions");
		Address address= new Address();
		address.setCountry("palestine");
		address.setCity("jerusalem");
		address.setNumber("253");
		address.setPostalCode("3256");
		address.setStreet("main street.");
		villa.setCost(2500);
		villa.setAddress(address);
		return villa;
	}

	@Test
	@Order(1)
	public void addVillaTest(){
		String addVillaUrl = "http://localhost:8080/api/villa/";
		RestTemplate restTemplate = new RestTemplate();
		Villa villa = createEmployee();
		villa.setId("98");

		HttpEntity<Villa> request = new HttpEntity<>(villa);
		ResponseEntity<Villa> result = restTemplate.postForEntity(addVillaUrl, request, Villa.class);
		if (villa != null){
			assertAll("villa",
					() -> assertEquals("98",villa.getId()),
					() -> assertEquals("lions",villa.getName()),
					() -> assertEquals("palestine",villa.getAddress().getCountry()),
					() -> assertEquals("jerusalem",villa.getAddress().getCity()),
					() -> assertEquals("253",villa.getAddress().getNumber()),
					() -> assertEquals("main street.",villa.getAddress().getStreet()),
					() -> assertEquals("3256",villa.getAddress().getPostalCode())
			);
		}
		Assert.hasText("200 OK",result.getStatusCode().toString());
	}

	@Test
	@Order(2)
	public void findByIdTest()  {
		Villa villa =villaRepository.findById("98").orElse(null);
		Assert.notNull(villa != null,"Villa does not exist");
		if (villa != null){
			assertAll("villa",
					() -> assertEquals("98",villa.getId()),
					() -> assertEquals("lions",villa.getName()),
					() -> assertEquals("palestine",villa.getAddress().getCountry()),
					() -> assertEquals("jerusalem",villa.getAddress().getCity()),
					() -> assertEquals("253",villa.getAddress().getNumber()),
					() -> assertEquals("main street.",villa.getAddress().getStreet()),
					() -> assertEquals("3256",villa.getAddress().getPostalCode())
			);
		}
	}

	@BeforeEach
	public void setUp() {
		// ensure geospatial index
		template.indexOps(Address.class).ensureIndex( new GeospatialIndex("position") );
	}

	@Test
	public void shouldFindAroundOrigin() {
		// when
		List<Villa> villas = villaRepository.findByPositionWithin( new Circle(0,0, 0.75) );

	}
}
