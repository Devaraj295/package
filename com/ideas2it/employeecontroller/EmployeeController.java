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
                choice =Integer.parseInt(scanner.nextLine());
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
            name = scanner.nextLine();
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
            emailId = scanner.nextLine();
            isValidEmail = ValidationUtil.isValidInput(ValidationUtil.emailIdPattern, emailId);
            traineeService.isCheckDuplicate(emailId);
            if (!(isValidEmail)) {
                logger.warn("Invalid Email Id Formate");
            } else if(isDuplicate(emailId)) {
                logger.warn("Already data is there");
                isValidEmail = false;
            }
        } while (!(isValidEmail));
        return emailId;
    }

    public boolean isDuplicate(String emailId) {
        boolean duplicate = true;
        boolean dublicateTrainer = trainerService.isCheckDuplicate(emailId);
        boolean dublicateTrainee = traineeService.isCheckDuplicate(emailId);
        if (dublicateTrainer == true && dublicateTrainee == true) {
            duplicate = false;
        }
        return duplicate;
    }


    public LocalDate getDateOfBirth() {
        String dateOfBirth;
        boolean isValidDateOfBirth = false;
        LocalDate date = null;
        do {
            try {
                logger.info("Enter the Employee Date of Birth yyyy-mm-dd :");
                dateOfBirth = scanner.nextLine();
                date = LocalDate.parse(dateOfBirth);
                if(isValidYear(date)) {
                    isValidDateOfBirth = true;
                } else {
                    System.out.println("your are not Eligible for work");
                    isValidDateOfBirth = false;
                 }
            } catch (Exception exception) {
                logger.error("Enter the valid Date of Birth");
                isValidDateOfBirth = false;
            }
        } while (!(isValidDateOfBirth));
        return date;
    }

    public boolean isValidYear(LocalDate date) {
        int age = getAge(date);
        if(age > 18 && age < 60) {
            return true;
        } else {
            return false;
        }
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
            phoneNumber = Long.parseLong(scanner.nextLine());
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
            experience = Float.parseFloat(scanner.nextLine());
            isValidExperience = ValidationUtil.isValidInput(ValidationUtil.
                                 experiencePattern, Float.toString(experience));
            if (!(isValidExperience)) {
                logger.warn("Enter valid year of Experience");
               isValidExperience = false;
            }
        } while (!(isValidExperience));
        return experience;
    }

    public void updateTraineeDetails() {
        if (traineeService.isCheckTraineeEmpty()) {
            logger.warn("Trainee List is Empty");
        } else {
            logger.info("Enter Trainee ID for update:");
            int id = Integer.parseInt(scanner.nextLine());
            int choice = 0;
            do {
                try {
                    traineeService.checkIndexById(id);
                    logger.info("Choose the option" + "\n"
                                + "1.Update Trainee Name" + "\n"
                                + "2.Update Trainee Date of Birth" + "\n"
                                + "3.Update Trainee Phone Number" + "\n"
                                + "4.Update Trainee Email ID" + "\n"
                                + "5.Go Back");
                    choice = Integer.parseInt(scanner.nextLine());
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

                        default:
                            logger.warn("Enter valid option only");
                            break;
                    }
                } catch (Exception exception) {
                    logger.error("Enter numbers only");
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
            logger.info("Trainee ID " + delete + " Deleted Successfully");
        }
    }

    public void trainerOperation() {
        Trainer trainer = new Trainer();
        int choice = 0;
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
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
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
        } while (choice != 6);       
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
        int choice = 0;
        int traineeID;
        int traineeIndex = 0;
        TraineeDAO traineeDAO = new TraineeDAO();
        List<Trainee> traineeAssignList = new ArrayList<Trainee>();
        do {
            try {
                logger.info("Choose the option " + "\n"
                            + "1.Assign Trainee from Trainee List" + "\n"
                            + "2.Create Trainee and Assign" + "\n"
                            + "3.Go Back ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        assignExisistTrainee();
                        break;

                    case 2:
                        addTraineeDetails();
                        traineeIndex = traineeDAO.traineeList.size()-1;
                        traineeAssignList.add(traineeService.getId(traineeIndex));
                        break;

                    case 3:
                        logger.info("Exit Assign trainee operation");
                        break;

                    default:
                        logger.warn("Enter valid option");
                        break;
                }
            } catch(Exception exception) {
              logger.error("Enter Numbers only");
            }
        } while (choice != 3);
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
        if(trainerService.isCheckTrainerListIsEmpty()) {
            logger.warn("Trainer List is Empty");
        } else {
            try {
                logger.info("Enter the Trainer ID to Update");
                int id = Integer.parseInt(scanner.nextLine());
                trainerService.checkIndex(id);       
                logger.info("Choose the option" + "\n"
                            + "1.Update Trainer Name" + "\n"
                            + "2.Update Trainer Date of Birth" + "\n"                             
                            + "3.update Trainer Phone Number" + "\n"
                            + "4.Update Trainer EmailId" + "\n" + "5.Go Back");
                int choice = Integer.parseInt(scanner.nextLine());
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
               
                    case 5:
                        logger.info("Exist Trainer updation");
                        break;

                    default :
                        logger.warn("Enter valid option");
                }
            } catch(Exception exception) {
                logger.error("Enter Numbers only");
            }
        }
    }

    public void readTrainerDetails() {
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
        if(trainerService.getTrainerDetails().isEmpty()) {
            logger.warn(" Trainer List is Empty");
        } else {
            logger.info("Enter the trainer ID :");
            Integer deleteTrainer = scanner.nextInt();
            trainerService.deleteTrainer(deleteTrainer); 
            logger.info("Trainer ID " + deleteTrainer
                              + " Deleted Sucessfully");
        }
    }
}
        








    
              


                       