package by.fro.domain.interactor;


import javax.inject.Inject;

import by.fro.domain.UseCase;
import by.fro.domain.executor.PostExecutionThread;
import by.fro.domain.executor.ThreadExecutor;
import by.fro.domain.repository.ApplicantRepository;
import io.reactivex.Observable;

public class CreateApplicantsUseCase extends UseCase<Long, Integer> {

    private final ApplicantRepository applicantRepository;

    @Inject
    CreateApplicantsUseCase(ApplicantRepository applicantRepository, ThreadExecutor threadExecutor,
                            PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.applicantRepository = applicantRepository;
    }

    @Override
    public Observable<Long> buildUseCaseObservable(Integer params){
        return this.applicantRepository.generateApplicantList();
    }
}
