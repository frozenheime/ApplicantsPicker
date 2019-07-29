package by.fro.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import by.fro.domain.UseCase;
import by.fro.domain.entity.Applicant;
import by.fro.domain.executor.PostExecutionThread;
import by.fro.domain.executor.ThreadExecutor;
import by.fro.domain.repository.ApplicantRepository;
import io.reactivex.Observable;

public class GetApplicantListUseCase extends UseCase<List<Applicant>, Void> {

    private final ApplicantRepository applicantRepository;

    @Inject
    GetApplicantListUseCase(ApplicantRepository applicantRepository, ThreadExecutor threadExecutor,
                            PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.applicantRepository = applicantRepository;
    }

    @Override
    public Observable<List<Applicant>> buildUseCaseObservable(Void params){
        return this.applicantRepository.getApplicantList();
    }
}
