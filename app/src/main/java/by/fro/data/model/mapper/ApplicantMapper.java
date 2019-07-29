package by.fro.data.model.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import by.fro.data.model.ApplicantLocalModel;
import by.fro.domain.entity.Applicant;

@Singleton
public class ApplicantMapper {

    @Inject
    ApplicantMapper(){}

    private Applicant transform(ApplicantLocalModel applicantLocalModel) {
        Applicant applicant = null;
        if (applicantLocalModel != null) {
            applicant = new Applicant();
            applicant.setName(applicantLocalModel.getName());
            applicant.setSurname(applicantLocalModel.getSurname());
            applicant.setAge(applicantLocalModel.getAge());
            applicant.setExperienceYears(applicantLocalModel.getExperienceYears());
            applicant.setSkill(Applicant.Skill.valueOf(applicantLocalModel.getSkill()));
        }
        return applicant;
    }

    public List<Applicant> transform(List<ApplicantLocalModel> applicantLocalModels){
        final List<Applicant> userList = new ArrayList<>(20);
        for (ApplicantLocalModel applicantLocalModel : applicantLocalModels) {
            final Applicant user = transform(applicantLocalModel);
            if (user != null) {
                userList.add(user);
            }
        }
        return userList;
    }
}
