package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;

public class MatchService {

    private MatchRepositoryImpl matchRepository;
    private ScoreRepositoryImpl scoreRepository;

    public MatchService(){
        this.matchRepository=new MatchRepositoryImpl();
        this.scoreRepository=new ScoreRepositoryImpl();
    }

    public Match createMatch(Match match){
        Match newMatch=this.matchRepository.create(match);
        match.getScore().setMatch(newMatch);
        this.scoreRepository.create(match.getScore());
        return newMatch;
    }
}
