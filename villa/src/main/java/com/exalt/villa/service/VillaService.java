package com.exalt.villa.service;

import com.exalt.villa.model.Villa;

import java.util.List;

public interface VillaService {

     List<Villa> findAll() ;

    Villa addVilla(Villa villaDto);

    Villa getVillaById(String id);


    Villa updateVilla(Villa villa,String id);

    String deleteVilla(String id);

    List<Villa> getAllWithPagination(int pageNo, int pageSize);

     List<Villa> findByAny(String searchText);

    List<Villa> getByDistance(float longitude, float latitude, int distance);

    List<Villa> getVillasGeo(double longitude, double latitude, double maxDistance);
}
