package com.mycompany.tennis.core;


import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.TournoiService;

import java.util.List;


public class TestDeConnection {
    public static void main(String... args){


        JoueurService joueurService=new JoueurService();

        TournoiService tournoiService=new TournoiService();

        List<Joueur> joueurList=joueurService.list();
        Tournoi tournoi= tournoiService.getById(4l);

        System.out.println(tournoi.getNom()+" "+tournoi.getCode());
        System.out.println("\n______________________\n");
        for(Joueur joueur: joueurList){
            System.out.println(joueur.getNom()+" "+joueur.getPrenom());
        }

    }
}
