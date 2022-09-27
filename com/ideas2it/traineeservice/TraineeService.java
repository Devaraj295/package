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
        return traineeDAO.getTraineeDetails();
    }

    public boolean isCheckTraineeEmpty() {
        return traineeDAO.traineeList.isEmpty();
    }

    public boolean isCheckTraineeId(Integer id) {
        boolean isValid = false;
        for (int index = 0; index < traineeDAO.traineeList.size(); index++) {
            if (id.equals(traineeDAO.traineeList.get(index).getId())) {
                isValid = true;
            }
        }
        return isValid;
    }

    public int checkIndexById(Integer id) {
        int traineeIndex = 0;
        for (int index = 0; index < traineeDAO.traineeList.size(); index++) {
            if (id.equals(traineeDAO.traineeList.get(traineeIndex).getId())) {
                traineeIndex = index;
                break;
            }
        }
        return traineeIndex;
    }

    public void updateName(Integer id, String name) {
        Integer traineeIndex = checkIndexById(id);
        Trainee trainee = getTrainee(traineeIndex);
        trainee.setName(name);
        traineeDAO.updateTrainee(traineeIndex, trainee);
    }

    public void updateAge(Integer id, int age) {
        Integer traineeIndex = checkIndexById(id);
        Trainee trainee = getTrainee(traineeIndex);
        trainee.setAge(age);
        traineeDAO.updateTrainee(traineeIndex, trainee);
    }

    public void updatePhoneNumber(Integer id, long phoneNumber) {
        Integer traineeIndex = checkIndexById(id);
        Trainee trainee = getTrainee(traineeIndex);
        trainee.setPhoneNumber(phoneNumber);
        traineeDAO.updateTrainee(traineeIndex, trainee);
    }
  
    public void updateEmailId(Integer id, String emailId) {
        Integer traineeIndex = checkIndexById(id);
        Trainee trainee = getTrainee(traineeIndex);
        trainee.setEmailId(emailId);
        traineeDAO.updateTrainee(traineeIndex, trainee);
    }

    public void updateDateOfBirth(Integer id, LocalDate dateOfBirth) {
        Integer traineeIndex = checkIndexById(id);
        Trainee trainee = getTrainee(traineeIndex);
        trainee.setDateOfBirth(dateOfBirth);
        traineeDAO.updateTrainee(traineeIndex, trainee);
    }

    public Trainee getId(Integer id) {
        Integer traineeIndex = checkIndexById(id);
        return traineeDAO.getTraineeDetails().get(traineeIndex);
    }

    public Trainee getTrainee(Integer id) {
        Integer traineeIndex = checkIndexById(id);
        return traineeDAO.getTraineeDetails().get(traineeIndex);
    }

    public void deleteId(Integer id) {
        Integer traineeIndex = checkIndexById(id);
        traineeDAO.deleteTrainee(traineeIndex);
    }
}

 