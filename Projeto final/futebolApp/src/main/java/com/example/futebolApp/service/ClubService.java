package com.example.futebolApp.service;

import com.example.futebolApp.exception.ResourceNotFoundException;
import com.example.futebolApp.model.Club;
import com.example.futebolApp.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Transactional
    public Club saveClub(Club club) {
        validateClub(club);
        return clubRepository.save(club);
    }

    @Transactional
    public Club updateClub(Long id, Club club) {
        if (!clubRepository.existsById(id)) {
            throw new ResourceNotFoundException("Club not found with id " + id);
        }
        validateClub(club);
        club.setId(id);
        return clubRepository.save(club);
    }

    @Transactional
    public void deactivateClub(Long id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Club not found with id " + id));
        club.setActive(false);
        clubRepository.save(club);
    }

    public Optional<Club> findById(Long id) {
        return clubRepository.findById(id);
    }

    public List<Club> findAll() {
        return clubRepository.findAll();
    }

    private void validateClub(Club club) {
        if (club.getName().length() < 2) {
            throw new IllegalArgumentException("Club name must have at least 2 characters");
        }
        if (club.getState().length() != 2) {
            throw new IllegalArgumentException("State must be 2 characters long");
        }
        if (club.getCreationDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Creation date cannot be in the future");
        }
        Optional<Club> existingClub = clubRepository.findByNameAndState(club.getName(), club.getState());
        if (existingClub.isPresent() && !existingClub.get().getId().equals(club.getId())) {
            throw new IllegalArgumentException("Club with the same name and state already exists");
        }
    }
}