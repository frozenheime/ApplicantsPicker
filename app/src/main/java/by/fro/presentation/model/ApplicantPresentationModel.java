package by.fro.presentation.model;

public class ApplicantPresentationModel {

    private String age;
    private String experienceYears;
    private String name;
    private String surname;
    private String skill;

    public String getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = "" + age;
    }

    public String getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = "" + experienceYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
