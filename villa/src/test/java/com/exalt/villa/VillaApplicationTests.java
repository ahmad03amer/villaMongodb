package com.exalt.villa;

import com.exalt.villa.model.Address;
import com.exalt.villa.model.Villa;
import com.exalt.villa.repository.VillaRepository;
import com.exalt.villa.service.VillaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;

//@IfProfileValue(name = ACTIVE_PROFILES_PROPERTY_NAME, value = "it-embedded")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableAutoConfiguration
//@DataMongoTest
class VillaApplicationTests {

	@Autowired
	private VillaRepository villaRepository;
	@Autowired
	private MongoTemplate template;
	@LocalServerPort
	private int localPort=8081;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private VillaService villaService;

	@Test
	void contextLoads() {
	}

	public Villa createVilla(){
		Villa villa = new Villa();
		villa.setName("stars");
		Address address= new Address();
		address.setCountry("palestine");
		address.setCity("Tubas");
		address.setNumber("253");
		address.setPostalCode("FS255");
		address.setStreet("main street.");
		GeoJsonPoint location =new GeoJsonPoint(23, 77);
		address.setLocation(location);
		villa.setCost(1650);
		villa.setAddress(address);
		return villa;
	}

	@Test
	@Order(1)
	public void addVillaTest(){
		String addVillaUrl = "http://localhost:"+localPort+"/api/villa/";
		RestTemplate restTemplate = new RestTemplate();
		Villa villa = createVilla();
		villa.setId("1");

		HttpEntity<Villa> request = new HttpEntity<>(villa);
		ResponseEntity<Villa> result = restTemplate.postForEntity(addVillaUrl, request, Villa.class);
		if (villa != null){
			assertAll("villa",
					() -> assertEquals("1",villa.getId()),
					() -> assertEquals("stars",villa.getName()),
					() -> assertEquals("palestine",villa.getAddress().getCountry()),
					() -> assertEquals("Tubas",villa.getAddress().getCity()),
					() -> assertEquals("253",villa.getAddress().getNumber()),
					() -> assertEquals("main street.",villa.getAddress().getStreet()),
					() -> assertEquals("FS255",villa.getAddress().getPostalCode())
			);
		}
		Assert.hasText("200 OK",result.getStatusCode().toString());
	}

	@Test
	@Order(2)
	public void findByIdTest()  {
		Villa villa =villaRepository.findById("1").orElse(null);
		Assert.notNull(villa != null,"Villa does not exist");
		if (villa != null){
			assertAll("villa",
					() -> assertEquals("1",villa.getId()),
					() -> assertEquals("stars",villa.getName()),
					() -> assertEquals("palestine",villa.getAddress().getCountry()),
					() -> assertEquals("Tubas",villa.getAddress().getCity()),
					() -> assertEquals("253",villa.getAddress().getNumber()),
					() -> assertEquals("main street.",villa.getAddress().getStreet()),
					() -> assertEquals("FS255",villa.getAddress().getPostalCode())
			);
		}
	}

	@BeforeEach
	public void setUp() {
		template.indexOps(Address.class).ensureIndex( new GeospatialIndex("address.location") );
	}

	@Test
	@Order(3)
	public void getVillasTest(){
		String getVillaUrl = "http://localhost:"+localPort+"/api/villa/";
		restTemplate = new RestTemplate();

		List<Villa> villas = (List<Villa>) restTemplate.getForObject(getVillaUrl, Villa.class);
		assertNotNull(villas,"villas Array is null!!");
	}

	@Test
	public void createVillaTest(){
		try{
			villaService.addVilla(createVilla());
		}catch (RuntimeException ex){
			ex.printStackTrace();
		}
	}
}
