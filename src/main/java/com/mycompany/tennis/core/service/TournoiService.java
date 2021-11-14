package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;

import java.util.List;

public class TournoiService {

    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService(){
        this.tournoiRepository=new TournoiRepositoryImpl();
    }

    public Tournoi create(Tournoi tournoi){
        return this.tournoiRepository.create(tournoi);
    }

    public void update(Tournoi tournoi){
        this.tournoiRepository.update(tournoi);
    }

    public Tournoi getById(Long id){
        return this.tournoiRepository.getById(id);
    }

    public void delete(Long id){
        this.tournoiRepository.delete(id);
    }

    public List<Tournoi> list(){
        return this.tournoiRepository.list();
    }

}
