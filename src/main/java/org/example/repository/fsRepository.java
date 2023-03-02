package org.example.repository;

import org.example.entity.SayingEntity;

import java.util.ArrayList;

public class fsRepository {

    ArrayList<SayingEntity> famousSayings = new ArrayList<>();
    public fsRepository() {
    }

    public ArrayList<SayingEntity> getFamousSayings() {
        return famousSayings;
    }

    public void setFamousSayings(ArrayList<SayingEntity> famousSayings) {
        this.famousSayings = famousSayings;
    }

    public fsRepository(ArrayList<SayingEntity> famousSayings) {
        this.famousSayings = famousSayings;
    }

    public void add(SayingEntity sayingEntity) {
        famousSayings.add(sayingEntity);
    }

    public void delete(int id) {
        famousSayings.remove(id);
    }

    public void modifiedAll (ArrayList<SayingEntity> famousSayings) {
        this.famousSayings = famousSayings;
    }
}
