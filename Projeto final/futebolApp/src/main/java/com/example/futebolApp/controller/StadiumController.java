package com.example.futebolApp.controller;

import com.example.futebolApp.model.Stadium;
import com.example.futebolApp.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stadiums")
public class StadiumController {

    @Autowired
    private StadiumService stadiumService;

    @PostMapping
    public ResponseEntity<Stadium> createStadium(@RequestBody Stadium stadium) {
        Stadium savedStadium = stadiumService.saveStadium(stadium);
        return ResponseEntity.status(201).body(savedStadium);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stadium> updateStadium(@PathVariable Long id, @RequestBody Stadium stadium) {
        Stadium updatedStadium = stadiumService.updateStadium(id, stadium);
        return ResponseEntity.ok(updatedStadium);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stadium> getStadium(@PathVariable Long id) {
        return stadiumService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Stadium>> listStadiums() {
        return ResponseEntity.ok(stadiumService.findAll());
    }
}