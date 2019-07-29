package by.fro.domain.entity;

/**
 * pojo for the base entity
 * */

public class Applicant {

    private String name;
    private String surname;
    private int age;
    private int experienceYears;
    private Skill skill;

    public enum Skill{
        LOW,
        AVERAGE,
        HIGH,
        OVERQUALIFIED,
        GODLIKE
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }


}
