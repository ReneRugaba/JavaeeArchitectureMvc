package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

import java.util.List;

public class JoueurService {

    private JoueurRepositoryImpl joueurRepository;

    public JoueurService(){
        this.joueurRepository=new JoueurRepositoryImpl();
    }

    public void create(Joueur joueur){
        this.joueurRepository.create(joueur);
    }

    public void update(Joueur joueur){
        this.joueurRepository.update(joueur);
    }

    public void delete(Long id){
        this.joueurRepository.delete(id);
    }

    public Joueur getById(Long id){
        return this.joueurRepository.getById(id);
    }

    public List<Joueur> list(){
        return this.joueurRepository.list();
    }
}
