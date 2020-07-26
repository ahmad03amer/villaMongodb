package com.exalt.villa.controller;

import com.exalt.villa.model.Villa;
import com.exalt.villa.service.VillaService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log
@RestController
@RequestMapping("/api/villa")
public class VillaController {

    @Autowired
    private VillaService villaService;

    @GetMapping("/")
    public List<Villa> findAll(){
        log.info("user entered to findAll villas");
       List<Villa> villas = villaService.findAll();
        return villas;
    }

    @PostMapping("/")
    public Villa addVilla(@RequestBody Villa villaDto) {
        log.info("user entered to addVilla ");
        return villaService.addVilla(villaDto);
    }

    @GetMapping("/{id}")
    public Villa getVillaById(@PathVariable String id) {
        log.info("user entered to addVilla ");
        return villaService.getVillaById(id);
    }

    @GetMapping("/find-by-distance")
    public List<Villa> getByDistance(@PathVariable float longitude , @PathVariable float latitude ,@PathVariable int distance) {
        return villaService.getByDistance( longitude ,  latitude ,  distance);
    }

    @GetMapping("/full-text-search/{searchText}")
    public List<Villa> findByAny(@PathVariable  String searchText) {
        return villaService.findByAny(searchText);
    }

    @GetMapping("/all-with-pagination")
    public List<Villa> getAllWithPagination(@RequestParam int pageNo, @RequestParam int pageSize) {
        log.info("user entered to getAllWithPagination");
        return villaService.getAllWithPagination(pageNo, pageSize);
    }

    @PutMapping("/{id}")
    public Villa updateVilla(@RequestBody Villa villa,@PathVariable String id ) {
        log.info("user entered to updateVilla");
        return villaService.updateVilla(villa,id);
    }

    @DeleteMapping("/{id}")
    public String deleteVilla(@PathVariable String id) {
        return villaService.deleteVilla(id);
    }

    @GetMapping("/git-by-geo/{longitude}/{latitude}/{maxDistance}")
    public List<Villa> getVillasGeo(@PathVariable double longitude , @PathVariable double latitude ,@PathVariable double maxDistance ){
        log.info("user entered to getVillasGeo");
        return villaService.getVillasGeo(longitude,latitude,maxDistance);
    }
}
