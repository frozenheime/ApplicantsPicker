package by.fro.data.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import by.fro.data.model.mapper.ApplicantMapper;
import by.fro.data.repository.datasource.HardcodedGodlikeApplicantDatasource;
import by.fro.data.repository.datasource.RandomGeneratorDatasource;
import by.fro.domain.entity.Applicant;
import by.fro.domain.repository.ApplicantRepository;
import io.reactivex.Observable;

public class ApplicantDataRepository implements ApplicantRepository {

    private final HardcodedGodlikeApplicantDatasource hardcodedGodlikeApplicantDatasource;
    private final RandomGeneratorDatasource randomGeneratorDatasource;
    private final ApplicantMapper mapper;

    @Inject
    ApplicantDataRepository(HardcodedGodlikeApplicantDatasource hardcodedGodlikeApplicantDatasource,
                            RandomGeneratorDatasource randomGeneratorDatasource, ApplicantMapper mapper) {
        this.hardcodedGodlikeApplicantDatasource = hardcodedGodlikeApplicantDatasource;
        this.randomGeneratorDatasource = randomGeneratorDatasource;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<Applicant>> getApplicantList() {
        return randomGeneratorDatasource.getRandomApplicantsList()
                .map(this.mapper::transform);
    }

    @Override
    public Observable<Long> generateApplicantList() {

        Observable<Long> hardcoded = hardcodedGodlikeApplicantDatasource.createSuperapplicant();
        Observable<List<Long>> randomed = randomGeneratorDatasource.populateApplicants();

        return randomed
                .flatMapIterable(item -> item)
                .mergeWith(hardcoded);
    }

    @Override
    public Observable<List<Applicant>> searchByName(String name) {
        return randomGeneratorDatasource.getApplicantsByName(name)
                .map(this.mapper::transform);
    }
}
