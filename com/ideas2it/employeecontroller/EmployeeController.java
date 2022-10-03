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
                       + "1.Add trainee details" + "\n"
                       + "2.Add trainer details" + "\n" + "3.Exit" + "\n");
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
                        logger.info("Exited application");
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
                           + "1.Add trainee details" + "\n"
                           + "2.Update trainee details" + "\n"
                           + "3.Delete trainee details" + "\n"
                           + "4.View trainee detail" + "\n"
                           + "5.Go back" + "\n");
                scanner = new Scanner(System.in);
                choice = Integer.parseInt(scanner.nextLine());
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
                        viewTraineeDetails();
                        break;

                    case 5:
                        logger.info("Existed trainee operation" + "\n");
                        break;

                    default :
                        logger.warn("Enter the correct option");
                        break;
                }
            } catch (Exception exception) {
                logger.error("Enter valid option only");
            }
        } while (choice != 5);
    }

    public void addTraineeDetails() {
        Trainee trainee = new Trainee();
        System.out.println("Trainee id :" + (traineeId));
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
            logger.info("Enter the employee name :");
            name = scanner.nextLine();
            isValidName = ValidationUtil.isValidInput
                                 (ValidationUtil.namePattern, name);
            if (!(isValidName)) {
                logger.warn("Enter character only" + "\n");
                isValidName = false;
            }
        } while (!(isValidName));
        return name;
    }

    public String getEmailId() {
        String emailId;
        boolean isValidEmail = false;
        do {
            logger.info("Enter employee email id: ");
            emailId = scanner.next();
            isValidEmail = ValidationUtil.isValidInput
                                          (ValidationUtil.emailIdPattern, emailId);
            if (!(isValidEmail)) {
                logger.warn("Invalid email id formate");
            } else if(isDuplicate(emailId)) {
                logger.warn("That user name is already here,try another");
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
                logger.info("Enter the employee date of birth yyyy-mm-dd :");
                dateOfBirth = scanner.nextLine();
                date = LocalDate.parse(dateOfBirth);
                if(isValidYear(date)) {
                    isValidDateOfBirth = true;
                } else {
                    logger.warn("Your are not eligible for work");
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
        if(date != null) {
            age = Period.between(date,currentDate).getYears();
        }
        return age;
    }

    public long getPhoneNumber() {
        boolean isValidPhoneNumber;
        long phoneNumber;
        do {
            logger.info("Enter the employee phone number :");
            phoneNumber = Long.parseLong(scanner.nextLine());
            isValidPhoneNumber = ValidationUtil.isValidInput
                                        (ValidationUtil.phoneNumberPattern,
                                        Long.toString(phoneNumber));
            if (!(isValidPhoneNumber)) {
                logger.warn("Enter the valid trainee phone number");
                isValidPhoneNumber = false;
            }
        } while (!(isValidPhoneNumber));
        return phoneNumber;
    }

    public float getExperience() {
        float experience;
        boolean isValidExperience = false;
        do {
            logger.info("Enter trainer experience");
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
        if (!(traineeService.isCheckTraineeEmpty())) {
            logger.info("Enter trainee id for update:");
            int id = Integer.parseInt(scanner.nextLine());
            int choice = 0;
            do {
                try {
                    traineeService.checkIndexById(id);
                    logger.info("Choose the option" + "\n"
                                + "1.Update trainee name" + "\n"
                                + "2.Update trainee date of birth" + "\n"
                                + "3.Update trainee phone number" + "\n"
                                + "4.Update trainee email id" + "\n"
                                + "5.Go back");
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
                            logger.info("Exited trainee updation");
                            break;

                        default:
                            logger.warn("Enter valid option only");
                            break;
                    }
                } catch (Exception exception) {
                    logger.error("Enter numbers only");
                }
            } while (choice != 5);
        } else  {
            logger.warn("Trainee list is empty");
        }
    }

     public void viewTraineeDetails() {
        List<Trainee> traineeDetail = traineeService.getAllDetails();
        if(!(traineeDetail.isEmpty())) {
            for (Trainee traineeList : traineeDetail) {
                logger.info(traineeList.toString());
            }
        } else {
            logger.warn("Trainee list is empty");
        }
    }

    public void deleteTraineeDetail() {
        if(!(traineeService.getAllDetails().isEmpty())) {
            logger.info("Enter the trainee id :");
            int delete = scanner.nextInt();
            traineeService.deleteId(delete);
            logger.info("Trainee id " + delete + " deleted successfully");
        } else {
            logger.warn("Trainee list is empty");
        }
    }

    public void trainerOperation() {
        Trainer trainer = new Trainer();
        int choice = 0;
        do {
            try { 
                logger.info("Choose the option" + "\n"
                            + "1.Add trainer details" + "\n"
                            + "2.Update trainer details" + "\n"
                            + "3.Delete trainer details" + "\n"
                            + "4.View trainer detail" + "\n" 
                            + "5.Assign trainees to trainer" + "\n"
                            + "6.Go back" + "\n");
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
                        viewTrainerDetails();
                        break;

                    case 5:
                        assignTrainee();
                        break;

                    case 6:
                        logger.info("Exited from trainer operation");
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
        System.out.println("Trainer id : " + trainerId);
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
                            + "1.Assign trainee from trainee list" + "\n"
                            + "2.Create trainee and assign" + "\n"
                            + "3.Go back ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        traineeAssignList.add(assignExisistTrainee());
                        break;

                    case 2:
                        addTraineeDetails();
                        traineeIndex = traineeDAO.traineeList.size()-1;
                        traineeAssignList.add(traineeService.getId(traineeIndex));
                        break;

                    case 3:
                        logger.info("Exited assign trainee operation");
                        break;

                    default:
                        logger.warn("Enter valid option");
                        break;
                }
            } catch(Exception exception) {
              logger.error("Enter numbers only");
            }
        } while (choice != 3);
        return traineeAssignList;
    }

    public Trainee assignExisistTrainee() {
        int traineeID;
        if (!(traineeService.isCheckTraineeEmpty())) {
            logger.info("Available trainee list id's :");
            for (Trainee trainee : traineeService.getAllDetails()) {
                logger.info(trainee.getId());
            }
            logger.info("Choose the trainee id to assign");
            traineeID = Integer.parseInt(scanner.nextLine());
            traineeService.getId(traineeId);
        } else {
            logger.warn("Trainee list is empty");
        }
        return traineeService.getId(traineeId);
    }

    public void updateTrainerDetails() {
        if (!(trainerService.isCheckTrainerListIsEmpty())) {
            try {
                logger.info("Enter the trainer id to update");
                int id = Integer.parseInt(scanner.nextLine());
                trainerService.checkIndex(id);     
                logger.info("Choose the option" + "\n"
                            + "1.Update trainer name" + "\n"
                            + "2.Update trainer date of birth" + "\n"                  
                            + "3.Update trainer phone number" + "\n"
                            + "4.Update trainer email id" + "\n" + "5.Go back");
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
                        logger.info("Existed trainer updation");
                        break;

                    default :
                        logger.warn("Enter valid option");
                }
            } catch(Exception exception) {
                logger.error("Enter numbers only");
            }
        } else {
            logger.warn("Trainer list is empty");
        }
    }

    public void viewTrainerDetails() {
        List<Trainer> trainerDetails = trainerService.getTrainerDetails();
        if(!(trainerDetails.isEmpty())) {
            for (Trainer trainer : trainerDetails) {
                logger.info(trainer);
                List<Trainee> traineeList = trainer.getTrainee();
                if (!(traineeList.isEmpty())) {
                    for(Trainee trainee : traineeList) {
                        logger.info(trainee.toString());
                    }
                }
            }
        } else {
            logger.warn("Traineer list is empty");
        }
    }

    public void deleteTrainerDetail() {
        if(!(trainerService.getTrainerDetails().isEmpty())) {
            logger.info("Enter the trainer id :");
            Integer deleteTrainer = scanner.nextInt();
            trainerService.deleteTrainer(deleteTrainer);
            logger.info("Trainer id " + deleteTrainer
                        + " deleted sucessfully");
        } else {
            logger.warn(" Trainer list is empty");
        }
    }
}
        








    
              


                       