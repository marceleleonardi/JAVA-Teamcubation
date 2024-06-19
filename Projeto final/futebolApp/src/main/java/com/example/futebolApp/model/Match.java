package com.example.futebolApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Home club is mandatory")
    @ManyToOne
    @JoinColumn(name = "home_club_id", nullable = false)
    private Club homeClub;

    @NotNull(message = "Away club is mandatory")
    @ManyToOne
    @JoinColumn(name = "away_club_id", nullable = false)
    private Club awayClub;

    @NotNull(message = "Result is mandatory")
    @Column(nullable = false)
    private String result;

    @NotNull(message = "Stadium is mandatory")
    @ManyToOne
    @JoinColumn(name = "stadium_id", nullable = false)
    private Stadium stadium;

    @NotNull(message = "Match date and time is mandatory")
    @FutureOrPresent(message = "Match date and time cannot be in the past")
    @Column(nullable = false)
    private LocalDateTime matchDateTime;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Club getHomeClub() {
        return homeClub;
    }

    public void setHomeClub(Club homeClub) {
        this.homeClub = homeClub;
    }

    public Club getAwayClub() {
        return awayClub;
    }

    public void setAwayClub(Club awayClub) {
        this.awayClub = awayClub;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public LocalDateTime getMatchDateTime() {
        return matchDateTime;
    }

    public void setMatchDateTime(LocalDateTime matchDateTime) {
        this.matchDateTime = matchDateTime;
    }
}
