package by.fro.data.repository.datasource;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import javax.inject.Inject;

import by.fro.core.R;
import by.fro.data.dao.ApplicantDao;
import by.fro.data.model.ApplicantLocalModel;
import io.reactivex.Observable;

public class RandomGeneratorDatasource {

    private ApplicantDao applicantDao;
    private Context context;

    @Inject
    public RandomGeneratorDatasource(ApplicantDao applicantDao, Context context) {
        this.applicantDao = applicantDao;
        this.context = context;
    }

    public Observable<List<ApplicantLocalModel>> getRandomApplicantsList(){
        return applicantDao.getApplicantsList().toObservable();
    }

    public Observable<List<Long>> populateApplicants(){
        return applicantDao.insertApplicantsList(generateApplicants()).toObservable();
    }

    public Observable<Long> insertApplicant(ApplicantLocalModel applicantLocalModel){
        return applicantDao.insertApplicant(applicantLocalModel).toObservable();
    }



    private List<ApplicantLocalModel> generateApplicants(){
        List<ApplicantLocalModel> applicantList = new ArrayList<>();
        String[] namesList = context.getResources().getStringArray(R.array.names);
        String[] surnamesList = context.getResources().getStringArray(R.array.surnames);
        Random random = new Random();
        String[] skills = {"LOW", "AVERAGE", "HIGH", "OVERQUALIFIED"};

        for (int i = 20; i>0; i--){
            String skill = "";
            int randomSkillPoints = random.nextInt(310);
            if (randomSkillPoints <= 100){
                skill = skills[0];
            } else if (randomSkillPoints <= 200){
                skill = skills[1];
            } else if (randomSkillPoints <= 300){
                skill = skills[2];
            } else if (randomSkillPoints <= 310) {
                skill = skills[3];
            }

            ApplicantLocalModel applicant = new ApplicantLocalModel();
            applicant.setName(namesList[random.nextInt(29)]);
            applicant.setSurname(surnamesList[random.nextInt(29)]);
            applicant.setAge(random.nextInt(30)+18);
            applicant.setExperienceYears(random.nextInt(30)+1);
            applicant.setSkill(skill);

            applicantList.add(applicant);
        }
        return applicantList;
    }

    public Observable<List<ApplicantLocalModel>> getApplicantsByName(String name) {
        return applicantDao.getApplicantsListByName(name).toObservable();
    }
}
