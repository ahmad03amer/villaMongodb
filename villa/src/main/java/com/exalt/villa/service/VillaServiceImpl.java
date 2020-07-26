package com.exalt.villa.service;

import com.exalt.villa.error.NotFoundExceptions;
import com.exalt.villa.model.Address;
import com.exalt.villa.model.Villa;
import com.exalt.villa.repository.VillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
//@CrossOrigin(origins = "http://localhost:3000")
public class VillaServiceImpl implements VillaService {

    @Autowired
    private VillaRepository villaRepository;

    @Transactional
    public Villa addVilla (Villa villaDto) {
        villaRepository.save(villaDto);
       if(true)
            throw new RuntimeException("Error Occurred");
        villaRepository.save(createVilla());
        return createVilla();
    }

    public List<Villa> getByDistance(float longitude, float latitude, int distance){
        Point basePoint = new Point(longitude,latitude);
        Distance raduis = new Distance(distance, Metrics.KILOMETERS);
        Circle area = new Circle(basePoint,raduis);
        Query query = new Query();
        query.addCriteria(Criteria.where("address.geoLocation").withinSphere(area));
        return null;
    }

    public Villa getVillaById(String id) {
        return villaRepository.findById(id).get();
    }


    public Villa updateVilla (Villa villa ,String id) {
        if(villaRepository.existsById(id)){
            return villaRepository.save(villa);
        }else {
            throw new NotFoundExceptions("the villa does not in the database");
        }
    }

    public String deleteVilla(String id) {
        villaRepository.deleteById(id);
        return "villa has been deleted.";
    }

    @Override
    public List<Villa> getAllWithPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return villaRepository.findAll(pageable).getContent();
    }

    public List<Villa> findByAny(String searchText){
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(searchText);
        Query query = TextQuery.queryText(criteria);
        query.addCriteria(Criteria.where("anIntegerProperty").is(1));
        Sort sort = Sort.by("cost");
        return villaRepository.findAllBy(criteria,sort);
    }

    public List<Villa> getVillasGeo(double longitude, double latitude , double maxDistance){
        return villaRepository.getVillasGeo(longitude,latitude,maxDistance);
    }

    public List<Villa> findAll(){
        return  villaRepository.findAll();
    }

    public Villa createVilla(){
        Villa villa = new Villa();
        villa.setId("6");
        villa.setName("holy land");
        Address address= new Address();
        address.setCountry("palestine");
        address.setCity("Yaffa");
        address.setNumber("253");
        address.setPostalCode("SF256");
        address.setStreet("main street.");
        GeoJsonPoint location =new GeoJsonPoint(7, 22);
        address.setLocation(location);
        villa.setCost(2500);
        villa.setAddress(address);
        return villa;
    }
}
