package by.fro.presentation.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import by.fro.domain.entity.Applicant;
import by.fro.presentation.di.PerActivity;
import by.fro.presentation.model.ApplicantPresentationModel;

@PerActivity
public class ApplicantPresentationMapper {

    @Inject
    public ApplicantPresentationMapper() {}

    public ApplicantPresentationModel transform(Applicant applicant) {
        if (applicant == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final ApplicantPresentationModel presentationModel = new ApplicantPresentationModel();
        presentationModel.setName(applicant.getName());
        presentationModel.setSurname(applicant.getSurname());
        presentationModel.setAge(applicant.getAge());
        presentationModel.setExperienceYears(applicant.getExperienceYears());
        presentationModel.setSkill(applicant.getSkill().toString());

        return presentationModel;
    }

    public Collection<ApplicantPresentationModel> transform(Collection<Applicant> usersCollection) {
        Collection<ApplicantPresentationModel> userModelsCollection;

        if (usersCollection != null && !usersCollection.isEmpty()) {
            userModelsCollection = new ArrayList<>();
            for (Applicant applicant : usersCollection) {
                userModelsCollection.add(transform(applicant));
            }
        } else {
            userModelsCollection = Collections.emptyList();
        }

        return userModelsCollection;
    }
}
