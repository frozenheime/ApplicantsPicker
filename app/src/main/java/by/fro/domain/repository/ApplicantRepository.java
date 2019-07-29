package by.fro.domain.repository;

import java.util.List;

import by.fro.domain.entity.Applicant;
import io.reactivex.Observable;

public interface ApplicantRepository {

    Observable<List<Applicant>> getApplicantList();

    Observable<Long> generateApplicantList();

    Observable<List<Applicant>> searchByName(String name);

}
