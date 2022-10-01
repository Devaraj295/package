package com.ideas2it.traineeservice;

import com.ideas2it.traineedao.TraineeDAO;
import com.ideas2it.trainee.Trainee;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class TraineeService {
    TraineeDAO traineeDAO = new TraineeDAO();

    public void addTraineeDetails(Trainee trainee) {
        traineeDAO.addTrainee(trainee);
    }

    public List<Trainee> getAllDetails() {
        return traineeDAO.getTrainee();
    }

    public boolean isCheckDuplicate(String emailId) {
        boolean isDuplicate = true;
        for (Trainee trainee : getAllDetails()) {
            String gmailId = trainee.getEmailId();
            if(emailId .equals(gmailId)) {
                isDuplicate = false;
            }
        }
        return isDuplicate;
    }

    public boolean isCheckTraineeEmpty() {
        return traineeDAO.traineeList.isEmpty();
    }

    public boolean isCheckTraineeId(Integer id) {
        boolean isValid = false;
        for (int index = 0; index < traineeDAO.getTrainee().size(); index++) {
            if (id.equals(traineeDAO.getTrainee().get(index).getId())) {
                isValid = true;
            }
        }
        return isValid;
    }

    public int checkIndexById(Integer id) {
        int traineeIndex = 0;
        for (int index = 0; index < traineeDAO.getTrainee().size(); index++) {
            if (id.equals(traineeDAO.getTrainee().get(traineeIndex).getId())) {
                traineeIndex = index;
                break;
            }
        }
        return traineeIndex;
    }

    public void updateName(int id, String name) {
        int traineeIndex = checkIndexById(id);
        Trainee trainee = getTrainee(traineeIndex);
        trainee.setName(name);
        traineeDAO.updateTrainee(traineeIndex, trainee);
    }

    public void updateAge(int id, int age) {
        int traineeIndex = checkIndexById(id);
        Trainee trainee = getTrainee(traineeIndex);
        trainee.setAge(age);
        traineeDAO.updateTrainee(traineeIndex, trainee);
    }

    public void updatePhoneNumber(int id, long phoneNumber) {
        int traineeIndex = checkIndexById(id);
        Trainee trainee = getTrainee(traineeIndex);
        trainee.setPhoneNumber(phoneNumber);
        traineeDAO.updateTrainee(traineeIndex, trainee);
    }
  
    public void updateEmailId(int id, String emailId) {
        int traineeIndex = checkIndexById(id);
        Trainee trainee = getTrainee(traineeIndex);
        trainee.setEmailId(emailId);
        traineeDAO.updateTrainee(traineeIndex, trainee);
    }

    public void updateDateOfBirth(int id, LocalDate dateOfBirth) {
        int traineeIndex = checkIndexById(id);
        Trainee trainee = getTrainee(traineeIndex);
        trainee.setDateOfBirth(dateOfBirth);
        traineeDAO.updateTrainee(traineeIndex, trainee);
    }

    public Trainee getId(int id) {
        int traineeIndex = checkIndexById(id);
        return traineeDAO.getTrainee().get(traineeIndex);
    }

    public Trainee getTrainee(int id) {
        int traineeIndex = checkIndexById(id);
        return traineeDAO.getTrainee().get(traineeIndex);
    }

    public void deleteId(int id) {
        int traineeIndex = checkIndexById(id);
        traineeDAO.deleteTrainee(traineeIndex);
    }
}

 