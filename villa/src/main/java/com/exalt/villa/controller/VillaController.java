package com.exalt.villa.controller;

import com.exalt.villa.model.Villa;
import com.exalt.villa.service.VillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/villa")
public class VillaController {

    @Autowired
    private VillaService villaService;

    @PostMapping("/")
    public Villa addVilla(@RequestBody Villa villa) {
        return villaService.addVilla(villa);
    }

    @GetMapping("/getById/{id}")
    public Villa getVillaById(@PathVariable String id) {
        return villaService.getVillaById(id);
    }

    @GetMapping("/fullTextSearch/{searchText}")
    public List<Villa> findByAny(@PathVariable  String searchText) {
        return villaService.findByAny(searchText);
    }

    @GetMapping("/allWithPagination")
    public List<Villa> getAllWithPagination(@RequestParam int pageNo,
                                              @RequestParam int pageSize) {
        return villaService.getAllWithPagination(pageNo, pageSize);
    }

    @PutMapping("/")
    public Villa updateVilla(@RequestBody Villa villa) {
        return villaService.updateVilla(villa);
    }

    @DeleteMapping("/{id}")
    public String deleteVilla(@PathVariable String id) {
        return villaService.deleteVilla(id);
    }
}
