package com.ideas2it.trainerservice;

import com.ideas2it.trainerdao.TrainerDAO;
import com.ideas2it.trainer.Trainer;

import java.util.ArrayList;
import java.util.List;

public class TrainerService {
    TrainerDAO trainerDAO = new TrainerDAO();

    public void addTrainerDetail(Trainer trainer) {
        trainerDAO.addTrainer(trainer);
    }

    public List<Trainer> getTrainerDetails() {
        return trainerDAO.getTrainer();
    }

    public boolean checkTrainerListIsEmpty() {
            return trainerDAO.trainerList.isEmpty();
    }

    public boolean checkTrainerId(Integer trainerId) {
        boolean isValid = false;
        for (int index = 0; index < trainerDAO.trainerList.size(); index++) {
            if ((trainerDAO.trainerList.get(index).getId()).equals(trainerId)) {
                isValid = true;
            }
        }
        return isValid;
   }

    public int checkIndex(Integer number) {
        int trainerIndex = 0;
        for (int index = 0; index < trainerDAO.trainerList.size(); index++) {
            if (number.equals(trainerDAO.trainerList.get(index).getId())) {
                trainerIndex = index;
            }
        }
        return trainerIndex;
    }

    public void updateName(Integer id, String name) {
        int index = checkIndex(id);
        Trainer trainer = getTrainer(index);
        trainer.setName(name);
        trainerDAO.updateTrainer(index, trainer);
    }

    public void updateAge(Integer id, int age) {
        int index = checkIndex(id);
        Trainer trainer = getTrainer(index);
        trainer.setAge(age);
        trainerDAO.updateTrainer(index, trainer);
    }

    public void updateDateOfBirth(Integer id, String dateOfBirth) {
        int index = checkIndex(id);
        Trainer trainer = getTrainer(index);
        trainer.setDateOfBirth(dateOfBirth);
        trainerDAO.updateTrainer(index, trainer);
    }

    public void updatePhoneNumber(Integer id, long phoneNumber) {
        int index = checkIndex(id);
        Trainer trainer = getTrainer(index);
        trainer.setPhoneNumber(phoneNumber);
        trainerDAO.updateTrainer(index, trainer);
    }

    public void updateEmailId(Integer id, String emailId) {
        int index = checkIndex(id);
        Trainer trainer = getTrainer(index);
        trainer.setEmailId(emailId);
        trainerDAO.updateTrainer(index, trainer);
    }

    public String searchId(Integer id) {
        int index = checkIndex(id);
        return trainerDAO.getTrainer().get(index).toString();
    }

    public Trainer getTrainer(Integer id) {
        int index = checkIndex(id);
        return trainerDAO.getTrainer().get(index);
    }

    public void deleteTrainer(Integer id) {
        int trainerIndex = checkIndex(id);
        trainerDAO.deleteTrainerId(trainerIndex);
    }
}