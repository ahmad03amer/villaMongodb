package com.exalt.villa.repository;

import com.exalt.villa.model.Villa;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillaRepository extends MongoRepository<Villa,String> {

    List<Villa> findAllBy(TextCriteria criteria, Sort sort);
    List<Villa> findByPositionWithin(Circle c);
}
