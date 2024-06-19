package com.example.futebolApp.service;

import com.example.futebolApp.exception.ResourceNotFoundException;
import com.example.futebolApp.model.Club;
import com.example.futebolApp.model.Match;
import com.example.futebolApp.repository.ClubRepository;
import com.example.futebolApp.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Transactional
    public Match saveMatch(Match match) {
        validateMatch(match);
        return matchRepository.save(match);
    }

    @Transactional
    public Match updateMatch(Long id, Match match) {
        if (!matchRepository.existsById(id)) {
            throw new ResourceNotFoundException("Match not found with id " + id);
        }
        validateMatch(match);
        match.setId(id);
        return matchRepository.save(match);
    }

    @Transactional
    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new ResourceNotFoundException("Match not found with id " + id);
        }
        matchRepository.deleteById(id);
    }

    public Optional<Match> findById(Long id) {
        return matchRepository.findById(id);
    }

    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    private void validateMatch(Match match) {
        if (match.getHomeClub().getId().equals(match.getAwayClub().getId())) {
            throw new IllegalArgumentException("Home club and away club cannot be the same");
        }
        Optional<Club> homeClub = clubRepository.findById(match.getHomeClub().getId());
        Optional<Club> awayClub = clubRepository.findById(match.getAwayClub().getId());
        if (!homeClub.isPresent() || !awayClub.isPresent()) {
            throw new IllegalArgumentException("Both clubs must exist");
        }
        if (!homeClub.get().getActive() || !awayClub.get().getActive()) {
            throw new IllegalArgumentException("Both clubs must be active");
        }
        if (match.getMatchDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Match date and time cannot be in the past");
        }
        if (match.getMatchDateTime().isBefore(homeClub.get().getCreationDate().atStartOfDay())
                || match.getMatchDateTime().isBefore(awayClub.get().getCreationDate().atStartOfDay())) {
            throw new IllegalArgumentException("Match date cannot be before the creation date of any club involved");
        }
    }
}
