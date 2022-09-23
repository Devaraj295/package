package com.ideas2it.trainee;

public class Trainee {
    private String name;
    private Integer age;
    private long phoneNumber;
    private String dateOfBirth;
    private Integer id;
    private String emailId;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

   public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }         

    public long  getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long  phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

   public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
   }

    public String  toString() {
        return ("Trainee ID :" + id + "\n" + "Employee name :" + name + "\n"
               + "Trainee Age :" + age + "\n"
               + "Trainee Phone Number :" + phoneNumber + "\n"
               + "Trainee Date of Birth :" + dateOfBirth + "\n"
               + "Trainee EmailId:" + emailId);
   }
}