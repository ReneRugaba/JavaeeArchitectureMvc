package com.mycompany.tennis.core;


import com.mycompany.tennis.core.entity.*;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.MatchService;
import com.mycompany.tennis.core.service.TournoiService;

import java.util.List;


public class TestDeConnection {
    public static void main(String... args){


        Joueur federer=new Joueur(){{
            setId(32l);
        }};
        Joueur morray=new Joueur(){{
            setId(34l);
        }};
        Epreuve epreuve=new Epreuve(){{
            setId(15l);
        }};
        Score score =new Score(){{
            setSet1((byte)3);
            setSet2((byte)5);
            setSet2((byte)6);
        }};
        Match match=new Match(){{
            setEpreuve(epreuve);
            setFinaliste(morray);
            setVainqueur(federer);
            setScore(score);
        }};

      Match match1=( new MatchService()).createMatch(match);

        System.out.println("match créé avec l'id "+match1.getId());

    }
}
