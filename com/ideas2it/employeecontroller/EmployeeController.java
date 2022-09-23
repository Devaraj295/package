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
                        logger.info("Exist Trainee operation");
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
                           + "5.Exit Trainee Details" + "\n");
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
                        logger.info("Existed Trainee operation");
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
        trainee.setAge(getAge());
        trainee.setDateOfBirth(getDateOfBirth());
        trainee.setPhoneNumber(getPhoneNumber());
        trainee.setEmailId(getEmailId());
        traineeService.addTraineeDetails(trainee);
    }

    public String getName() {
        String name;
        boolean isValidTraineeName = false;
        do {
            logger.info("Enter the Employee Name :");
            name = scanner.next();
            isValidTraineeName = ValidationUtil.isValidInput
                                 (ValidationUtil.namePattern, name);
            if (!(isValidTraineeName)) {
                logger.warn("Enter Character only");
                isValidTraineeName = false;
            }
        } while (!(isValidTraineeName));
        return name;
    }

    public int getAge() {
        boolean isValidTraineeAge = false;
        int age;
        do {
            logger.info("Enter the Employee Age :");
            age = Integer.parseInt(scanner.next());
            isValidTraineeAge = ValidationUtil.isValidInput
                                (ValidationUtil.agePattern, 
                                Integer.toString(age));
            if (!(isValidTraineeAge)) {
                logger.warn("Enter the valid Age");
                isValidTraineeAge = false;
            } else  {
                try {
                    if ((age > 18) && (age < 60)) {
                        isValidTraineeAge = true;
                        return age;  
                    } else {
                        throw new InvalidInputException("Invalid Age");
                    }
                } catch (InvalidInputException exception) {
                    logger.error("your are not Eligible for work " + exception);
                    isValidTraineeAge = false;
                }
            }
        } while (!(isValidTraineeAge));
        return age;
    }

    public String getEmailId() {
        String emailId;
        boolean isValid = false;
        do {
            logger.info("Enter employee email ID: ");
            emailId = scanner.next();
            isValid = ValidationUtil.isValidInput(ValidationUtil.emailIdPattern, emailId);
            if (!(isValid)) {
                logger.warn("Invalid Email Id Formate");
            }
        } while (!(isValid));
        return emailId;
    }

    public String getDateOfBirth() {
        boolean isCheck;
        Trainee trainee = new Trainee();
        String dateOfBirth = " ";
        boolean isValidTraineeDateOfBirth = true;
        do {
            try {
                logger.info("Enter the Employee Date of Birth :");
                dateOfBirth = scanner.next();
                LocalDate date = LocalDate.parse(dateOfBirth);
                trainee.setDateOfBirth(dateOfBirth);
                isCheck = false;
            } catch (Exception e) {
                logger.error("Enter the valid Date of Birth");
                isCheck = true;
            }
        } while (!(isValidTraineeDateOfBirth));
        return (dateOfBirth);
    }

    public long getPhoneNumber() {
        boolean isValidTraineePhoneNumber;
        long phoneNumber;
        do {
            logger.info("Enter the Employee phone number :");
            phoneNumber = scanner.nextLong();
            isValidTraineePhoneNumber = ValidationUtil.isValidInput
                                        (ValidationUtil.phoneNumberPattern, 
                                        Long.toString(phoneNumber));
            if (!(isValidTraineePhoneNumber)) {
                logger.warn("Enter the valid Trainee PhoneNumber");
            }
        } while (!(isValidTraineePhoneNumber));
        return (phoneNumber);
    }

    public void updateTraineeDetails() {
        logger.info("Enter Trainee ID for update:");
        int id = scanner.nextInt();
        if (traineeService.checkTraineeEmpty()) {
            logger.warn("Trainee List is Empty");
        } else {
            traineeService.checkIndexById(id);
            logger.info("Choose the option" + "\n"
                       + "1.Update Trainee Name" + "\n"
                       + "2.Update Trainee Age" + "\n"
                       + "3.Update Trainee Date of Birth" + "\n"
                       + "4.Update Trainee Phone Number" + "\n"
                       + "5.Update Trainee Email ID");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    traineeService.updateName(id, getName());
                    break;

                case 2:
                    traineeService.updateAge(id, getAge());
                    break;

                case 3:
                    traineeService.updateDateOfBirth(id, getDateOfBirth());
                    break;

                case 4:
                    traineeService.updatePhoneNumber(id, getPhoneNumber());
                    break;

                case 5:
                    traineeService.updateEmailId(id, getEmailId());
                    break;

                case 6:
                    logger.info("Exit Trainee Updation");
                    break;
            }
        }
    }

     public void readTraineeDetails() {
        List<Trainee> traineeDetail = traineeService.getAllDetails();
        if(traineeDetail.isEmpty()) {
            System.out.println("Trainee List is Empty");
        } else {
            for (Trainee traineeList : traineeDetail) {
                System.out.println(traineeList.toString());
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
                            + "6.Exit" + "\n");
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
        trainer.setAge(getAge());
        trainer.setDateOfBirth(getDateOfBirth());
        trainer.setEmailId(getEmailId());
        trainer.setPhoneNumber(getPhoneNumber());
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
                       + "3.Exit ");
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

                default:
                    logger.warn("Enter valid option");
            }
        } while (assignTraineeChoise != 3);
        return traineeAssignList;
    }

    public void assignExisistTrainee() {
        int traineeID;
        List<Trainee> traineeAssignList = new ArrayList<Trainee>();
        if (traineeService.checkTraineeEmpty()) {
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
        if(trainerService.checkTrainerListIsEmpty()) {
            logger.warn("Trainer List is Empty");
        } else {
            trainerService.checkIndex(id);         
            logger.info("1.Update Trainer Name" + "\n" 
                        + "2.Update Trainer Age" + "\n"
                        + "3.Update Trainer Date of Birth" + "\n"                              
                        + "4.update Trainer Phone Number" + "\n"
                        + "5.Update Trainer EmailId");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    trainerService.updateName(id, getName());
                    break;

                case 2:
                    trainerService.updateAge(id, getAge());
                    break;

                case 3:
                    trainerService.updateDateOfBirth(id, getDateOfBirth());
                    break;

                case 4:
                    trainerService.updatePhoneNumber(id, getPhoneNumber());
                    break;

                case 5:
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
        








    
              


                       