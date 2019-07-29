package by.fro.data.repository.datasource;

import javax.inject.Inject;

import by.fro.data.dao.ApplicantDao;
import by.fro.data.model.ApplicantLocalModel;
import io.reactivex.Observable;

public class HardcodedGodlikeApplicantDatasource {

    private ApplicantDao applicantDao;

    @Inject
    public HardcodedGodlikeApplicantDatasource(ApplicantDao applicantDao) {
        this.applicantDao = applicantDao;
    }

    public Observable<Long> createSuperapplicant(){
        ApplicantLocalModel applicant = new ApplicantLocalModel();
        applicant.setName("Vitali");
        applicant.setSurname("Moroz");
        applicant.setAge(29);
        applicant.setExperienceYears(3);
        applicant.setSkill("GODLIKE");
        return applicantDao.insertApplicant(applicant).toObservable();
    }
}
