package com.exalt.villa.service;

import com.exalt.villa.error.InputNotValid;
import com.exalt.villa.error.NotFoundExceptions;
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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@Component
@CrossOrigin(origins = "http://localhost:3000")
public class VillaServiceImpl implements VillaService {

    @Autowired
    private VillaRepository villaRepository;

    public Villa addVilla (Villa villaDto) {
        return villaRepository.save(villaDto);
    }

    public List<Villa> getByDistance(float longitude, float latitude, int distance){
       Point basePoint = new Point(longitude,latitude);
        Distance raduis = new Distance(distance, Metrics.KILOMETERS);
        Circle area = new Circle(basePoint,raduis);
        Query query = new Query();
        query.addCriteria(Criteria.where("address.geoLocation").withinSphere(area));
        return null;
    }

    @Transactional(readOnly = true)
    public Villa getVillaById(String id) {
        return villaRepository.findById(id).get();
    }


    @Transactional
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

    @Transactional(readOnly = true)
    public List<Villa> getVillasGeo(double longitude, double latitude , double maxDistance){
     return villaRepository.getVillasGeo(longitude,latitude,maxDistance);
    }

    public List<Villa> findAll(){
        return  villaRepository.findAll();
    }

}
