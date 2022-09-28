package com.ideas2it.trainerservice;

import com.ideas2it.trainerdao.TrainerDAO;
import com.ideas2it.trainer.Trainer;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class TrainerService {
    TrainerDAO trainerDAO = new TrainerDAO();

    public void addTrainerDetail(Trainer trainer) {
        trainerDAO.addTrainer(trainer);
    }

    public List<Trainer> getTrainerDetails() {
        return trainerDAO.getTrainer();
    }
    
    public boolean isCheckDuplicate(String emailId) {
        boolean isDuplicate = true;
        for (Trainer trainer : getTrainerDetails()) {
            String gmailId = trainer.getEmailId(emailId);
            if(emailId == gmailId) {
                isDuplicate = false;
            }
        }
        return isDuplicate;
    }

    public boolean isCheckTrainerListIsEmpty() {
            return trainerDAO.trainerList.isEmpty();
    }

    public boolean isCheckTrainerId(Integer trainerId) {
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
        for (int index = 0; index < trainerDAO.getTrainer().size(); index++) {
            if (number.equals(trainerDAO.getTrainer().get(index).getId())) {
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

    public void updateExperience(Integer id, float experience) {
        int index = checkIndex(id);
        Trainer trainer = getTrainer(index);
        trainer.setExperience(experience);
        trainerDAO.updateTrainer(index, trainer);
    }

    public void updateDateOfBirth(Integer id, LocalDate dateOfBirth) {
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
        int index = checkIndex(id);
        trainerDAO.deleteTrainerId(index);
    }
}