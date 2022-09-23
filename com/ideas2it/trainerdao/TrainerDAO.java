package com.ideas2it.trainerdao;

import com.ideas2it.trainer.Trainer;

import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {
    public static List<Trainer> trainerList = new ArrayList<Trainer>();

    public void addTrainer(Trainer trainer) {
        trainerList.add(trainer);
    }

    public List<Trainer> getTrainer() {
        return trainerList;
    }

    public void updateTrainer(int index, Trainer trainer) {
        trainerList.set(index, trainer);
    }

    public void deleteTrainerId(int index) {
        trainerList.remove(index);
    } 
}
