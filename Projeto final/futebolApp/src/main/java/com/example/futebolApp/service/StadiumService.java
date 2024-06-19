package com.example.futebolApp.service;

import com.example.futebolApp.exception.ResourceNotFoundException;
import com.example.futebolApp.model.Stadium;
import com.example.futebolApp.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StadiumService {

    @Autowired
    private StadiumRepository stadiumRepository;

    @Transactional
    public Stadium saveStadium(Stadium stadium) {
        validateStadium(stadium);
        return stadiumRepository.save(stadium);
    }

    @Transactional
    public Stadium updateStadium(Long id, Stadium stadium) {
        if (!stadiumRepository.existsById(id)) {
            throw new ResourceNotFoundException("Stadium not found with id " + id);
        }
        validateStadium(stadium);
        stadium.setId(id);
        return stadiumRepository.save(stadium);
    }

    public Optional<Stadium> findById(Long id) {
        return stadiumRepository.findById(id);
    }

    public List<Stadium> findAll() {
        return stadiumRepository.findAll();
    }
    private void validateStadium(Stadium stadium) {
        if (stadium.getName().length() < 3) {
            throw new IllegalArgumentException("Stadium name must have at least 3 characters");
        }
        Optional<Stadium> existingStadium = stadiumRepository.findByName(stadium.getName());
        if (existingStadium.isPresent() && !existingStadium.get().getId().equals(stadium.getId())) {
            throw new IllegalArgumentException("Stadium with the same name already exists");
        }
    }
}