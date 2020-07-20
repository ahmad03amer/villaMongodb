package com.exalt.villa.service;

import com.exalt.villa.model.Villa;

import java.util.List;

public interface VillaService {
    Villa addVilla(Villa villa);

    Villa getVillaById(String id);


    Villa updateVilla(Villa villa);

    String deleteVilla(String id);

    List<Villa> getAllWithPagination(int pageNo, int pageSize);

     List<Villa> findByAny(String searchText);
}
