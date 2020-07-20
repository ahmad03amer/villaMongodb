package com.exalt.villa.service;

import com.exalt.villa.model.Villa;
import com.exalt.villa.repository.VillaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VillaServiceImpl implements VillaService {

    @Autowired
    private VillaRepository villaRepository;

    public Villa addVilla (Villa villa) {
        return villaRepository.save(villa);
    }


    public Villa getVillaById(String id) {
        return villaRepository.findById(id).get();
    }


    public Villa updateVilla (Villa villa) {
        return villaRepository.save(villa);
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
}
