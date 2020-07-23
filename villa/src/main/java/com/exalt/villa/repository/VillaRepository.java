package com.exalt.villa.repository;

import com.exalt.villa.model.Villa;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillaRepository extends MongoRepository<Villa,String> {

    List<Villa> findAllBy(TextCriteria criteria, Sort sort);

    @Query("{\n" +
            "    \"address.location\": {\n" +
            "        $near: {\n" +
            "            $geometry:\n" +
            "                { type: \"Point\", coordinates: [?0,?1] }, $maxDistance:?2\n" +
            "        }\n" +
            "    }\n" +
            "}")
    List<Villa> getVillasGeo(@Param("longitude") double longitude, @Param("latitude") double latitude , @Param("maxDistance")double maxDistance);
}
