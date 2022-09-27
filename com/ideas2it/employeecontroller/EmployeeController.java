package com.ideas2it.employeecontroller;

import com.ideas2it.invalidinputexception.InvalidInputException;
import com.ideas2it.traineedao.TraineeDAO;
import com.ideas2it.trainerdao.TrainerDAO;
import com.ideas2it.trainee.Trainee;
import com.ideas2it.traineeservice.TraineeService;
import com.ideas2it.trainer.Trainer;
import com.ideas2it.trainerservice.TrainerService;
import com.ideas2it.validationutil.ValidationUtil;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class EmployeeController {
    int trainerId = 1;
    int traineeId = 1;
    Scanner scanner = new Scanner(System.in);
    TraineeService traineeService = new TraineeService();
    TrainerService trainerService = new TrainerService();
    static Logger logger = LogManager.getLogger(EmployeeController.class);

    public static void main(String[] args) {
        int choice = 0;
        do {
            EmployeeController controller = new EmployeeController();
            Scanner scan = new Scanner(System.in);
            logger.info("\n" + "Choose the option" + "\n"
                       + "1.Add Trainee Details" + "\n"
                       + "2.Add Trainer Details" + "\n" + "3.Exit" + "\n");
            try {
                choice = scan.nextInt();
                switch(choice) {
                    case 1:
                        controller.traineeOperation();
                        break;

                    case 2:
                        controller.trainerOperation();
                        break;

                    case 3:
                        logger.info("Exit Application");
                        break;

                    default:
                        logger.warn("Enter valid option");
                }
            } catch (Exception exception) {
                logger.error("Enter Number only");
            }
        } while (choice != 3);
    }

    public void traineeOperation() {
        int choice = 0;
        do {
            try {
                logger.info("\n" + "Choose the option" + "\n"
                           + "1.Add Trainee Details" + "\n"
                           + "2.Update Trainee Details" + "\n"
                           + "3.Delete Trainee Details" + "\n"
                           + "4.Read Trainee Detail" + "\n"
                           + "5.Go Back" + "\n");
                scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        addTraineeDetails();
                        break;

                    case 2:
                        updateTraineeDetails();
                        break;

                    case 3:
                        deleteTraineeDetail();
                        break;

                    case 4:
                        readTraineeDetails();
                        break;

                    case 5:
                        logger.info("Existed Trainee operation" + "\n");
                        break;

                    default :
                        logger.warn("Enter the correct option");
                        break;
                }
            } catch (Exception exception) {
                logger.error("Enter Valid option only");
            }
        } while (choice != 5);
    }

    public void addTraineeDetails() {
        Trainee trainee = new Trainee();
        TraineeDAO traineeDAO = new TraineeDAO();
        System.out.println("Trainee ID :" + (traineeId));
        trainee.setId(traineeId++);
        trainee.setName(getName());
        LocalDate dateOfBirth = getDateOfBirth();
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setAge(getAge(dateOfBirth));
        trainee.setPhoneNumber(getPhoneNumber());
        trainee.setEmailId(getEmailId());
        traineeService.addTraineeDetails(trainee);
    }

    public String getName() {
        String name;
        boolean isValidName = false;
        do {
            logger.info("Enter the Employee Name :");
            name = scanner.next();
            isValidName = ValidationUtil.isValidInput
                                 (ValidationUtil.namePattern, name);
            if (!(isValidName)) {
                logger.warn("Enter Character only" + "\n");
                isValidName = false;
            }
        } while (!(isValidName));
        return name;
    }

    public String getEmailId() {
        String emailId;
        boolean isValidEmail = false;
        do {
            logger.info("Enter Employee email ID: ");
            emailId = scanner.next();
            isValidEmail = ValidationUtil.isValidInput(ValidationUtil.emailIdPattern, emailId);
            if (!(isValidEmail)) {
                logger.warn("Invalid Email Id Formate");
            }
        } while (!(isValidEmail));
        return emailId;
    }

    public LocalDate getDateOfBirth() {
        String dateOfBirth;
        boolean isValidDateOfBirth = false;
        LocalDate date = null;
        do {
            try {
                logger.info("Enter the Employee Date of Birth :");
                dateOfBirth = scanner.next();
                date = LocalDate.parse(dateOfBirth);
                isValidDateOfBirth = true;
            } catch (Exception exception) {
                logger.error("Enter the valid Date of Birth");
                isValidDateOfBirth = false;
            }
        } while (!(isValidDateOfBirth));
        return date;
    }

    public int getAge(LocalDate date) {
        int age = 0;
        LocalDate currentDate = LocalDate.now();
        if((date != null) && (currentDate != null)) {
            age = Period.between(date,currentDate).getYears();
        }
        return age;
    }

    public long getPhoneNumber() {
        boolean isValidPhoneNumber;
        long phoneNumber;
        do {
            logger.info("Enter the Employee phone number :");
            phoneNumber = scanner.nextLong();
            isValidPhoneNumber = ValidationUtil.isValidInput
                                        (ValidationUtil.phoneNumberPattern,
                                        Long.toString(phoneNumber));
            if (!(isValidPhoneNumber)) {
                logger.warn("Enter the valid Trainee PhoneNumber");
                isValidPhoneNumber = false;
            }
        } while (!(isValidPhoneNumber));
        return phoneNumber;
    }

    public float getExperience() {
        float experience;
        boolean isValidExperience = false;
        do {
            logger.info("Enter Trainer experience");
            experience = scanner.nextFloat();
            isValidExperience = ValidationUtil.isValidInput(ValidationUtil.
                                 experiencePattern, Float.toString(experience));
            if (!(isValidExperience)) {
                logger.warn("Enter valid formate");
               isValidExperience = false;
            }
        } while (!(isValidExperience));
        return experience;
    }

    public void updateTraineeDetails() {
        logger.info("Enter Trainee ID for update:");
        int id = scanner.nextInt();
        int choice = 0;
        if (traineeService.isCheckTraineeEmpty()) {
            logger.warn("Trainee List is Empty");
        } else {
            do {
                traineeService.checkIndexById(id);
                logger.info("Choose the option" + "\n"
                            + "1.Update Trainee Name" + "\n"
                            + "2.Update Trainee Date of Birth" + "\n"
                            + "3.Update Trainee Phone Number" + "\n"
                            + "4.Update Trainee Email ID" + "\n"
                            + "5.Go Back");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        traineeService.updateName(id, getName());
                        break;

                    case 2:
                        traineeService.updateDateOfBirth(id, getDateOfBirth());
                        break;

                    case 3:
                        traineeService.updatePhoneNumber(id, getPhoneNumber());
                        break;

                    case 4:
                        traineeService.updateEmailId(id, getEmailId());
                        break;

                    case 5:
                        logger.info("Exit Trainee Updation");
                        break;
                }
            } while (choice != 5);
        }
    }

     public void readTraineeDetails() {
        List<Trainee> traineeDetail = traineeService.getAllDetails();
        if(traineeDetail.isEmpty()) {
            logger.warn("Trainee List is Empty");
        } else {
            for (Trainee traineeList : traineeDetail) {
                logger.info(traineeList.toString());
            }
        }
    }

    public void deleteTraineeDetail() {
        if(traineeService.getAllDetails().isEmpty()) {
            logger.warn("Trainee List is Empty");
        } else {
            logger.info("Enter the trainee ID :");
            int delete = scanner.nextInt();
            traineeService.deleteId(delete);
            logger.info("Trainee ID" + delete + "Deleted Successfully");
        }
    }

    public void trainerOperation() {
        Trainer trainer = new Trainer();
        int trainerUserOption = 0;
        do {
            try { 
                logger.info("choose the option" + "\n"
                            + "1.Add Trainer Details" + "\n"
                            + "2.Update Trainer Details" + "\n"
                            + "3.Delete Trainer Details" + "\n"
                            + "4.Read Trainer Detail" + "\n" 
                            + "5.Assign Trainees to Trainer" + "\n"
                            + "6.Go Back" + "\n");
                scanner = new Scanner(System.in);
                trainerUserOption = scanner.nextInt();
                switch (trainerUserOption) {
                    case 1:
                        addTrainerDetails();
                        break;

                    case 2:
                        updateTrainerDetails();
                        break;

                    case 3:
                        deleteTrainerDetail();
                        break;

                    case 4:
                        readTrainerDetails();
                        break;

                    case 5:
                        assignTrainee();
                        break;

                    case 6:
                        logger.info("Exit from Trainer operation");
                        break;

                    default:
                        logger.warn("Enter valid option");
                }
            } catch (Exception exception) {
                logger.error("Enter numbers only");
            }
        } while (trainerUserOption != 6);       
    }

    public void addTrainerDetails() {
        Trainer trainer = new Trainer();
        TrainerDAO trainerDAO = new TrainerDAO();
        System.out.println("Trainer ID : " + trainerId);
        trainer.setId(trainerId++);
        trainer.setName(getName());
        trainer.setExperience(getExperience());
        LocalDate dateOfBirth = getDateOfBirth();
        trainer.setDateOfBirth(dateOfBirth);
        trainer.setAge(getAge(dateOfBirth));
        trainer.setPhoneNumber(getPhoneNumber());
        trainer.setEmailId(getEmailId());
        trainer.setTrainee(assignTrainee());
        trainerService.addTrainerDetail(trainer);
    }

    public List<Trainee> assignTrainee() {
        int assignTraineeChoise;
        int traineeID;
        int traineeIndex = 0;
        TraineeDAO traineeDAO = new TraineeDAO();
        List<Trainee> traineeAssignList = new ArrayList<Trainee>();
        do {
            logger.info("Choose the option " + "\n"
                       + "1.Assign Exisist Trainee" + "\n"
                       + "2.Create Trainee and Assign" + "\n"
                       + "3.Go Back ");
            assignTraineeChoise = scanner.nextInt();
            switch (assignTraineeChoise) {
                case 1:
                   assignExisistTrainee();
                   break;

                case 2:
                    addTraineeDetails();
                    traineeIndex = traineeDAO.traineeList.size()-1;
                    traineeAssignList.add(traineeService.getId(traineeIndex));
                    break;

                case 3:
                    logger.info("");
                    break;

                default:
                    logger.warn("Enter valid option");
            }
        } while (assignTraineeChoise != 3);
        return traineeAssignList;
    }

    public void assignExisistTrainee() {
        int traineeID;
        List<Trainee> traineeAssignList = new ArrayList<Trainee>();
        if (traineeService.isCheckTraineeEmpty()) {
            logger.warn("Trainee List is Empty");
        } else {
            logger.info("Available Trainee List ID's :");
            for (Trainee trainee : traineeService.getAllDetails()) {
                logger.info(trainee.getId());
            }
            logger.info("Choose the Trainee ID to Assign");
            traineeID = scanner.nextInt();
            traineeAssignList.add(traineeService.getId(traineeId));
        }
    }     
      
    public void updateTrainerDetails() {
        logger.info("Enter the Trainer ID to Update");
        int id = scanner.nextInt();
        if(trainerService.isCheckTrainerListIsEmpty()) {
            logger.warn("Trainer List is Empty");
        } else {
            trainerService.checkIndex(id);       
            logger.info("Choose the option" + "\n" 
                        + "1.Update Trainer Name" + "\n"
                        + "2.Update Trainer Date of Birth" + "\n"                              
                        + "3.update Trainer Phone Number" + "\n"
                        + "4.Update Trainer EmailId");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    trainerService.updateName(id, getName());
                    break;

                case 2:
                    trainerService.updateDateOfBirth(id, getDateOfBirth());
                    break;

                case 3:
                    trainerService.updatePhoneNumber(id, getPhoneNumber());
                    break;

                case 4:
                    trainerService.updateEmailId(id, getEmailId());
                    break;

                default :
                    logger.warn("Enter valid option");
            }
        }
    }

    public void readTrainerDetails() {
        TrainerDAO trainerDAO = new TrainerDAO();
        List<Trainer> trainerDetails = trainerService.getTrainerDetails();
        if(trainerDetails.isEmpty()) {
            logger.warn("Traineer List is Empty");
        } else {
            for (Trainer trainer : trainerDetails) {
                System.out.println(trainer);
                List<Trainee> traineeList = trainer.getTrainee();
                if (!(traineeList.isEmpty())) {
                    for(Trainee trainee : traineeList) {
                        logger.info(trainee.toString());
                    }
                }
            }
        }
    }

    public void deleteTrainerDetail() {
        TrainerDAO trainerDAO = new TrainerDAO();
        if(trainerService.getTrainerDetails().isEmpty()) {
            logger.warn(" Trainer List is Empty");
        } else {
            logger.info("Enter the trainer ID :");
            Integer deleteTrainer = scanner.nextInt();
            trainerService.deleteTrainer(deleteTrainer);
            logger.info("Trainer ID " + deleteTrainer
                              + "Deleted Sucessfully");
        }
    }
}
        








    
              


                       