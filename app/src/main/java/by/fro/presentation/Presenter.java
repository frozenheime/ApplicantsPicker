package by.fro.presentation;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import by.fro.core.BuildConfig;
import by.fro.domain.entity.Applicant;
import by.fro.domain.interactor.CreateApplicantsUseCase;
import by.fro.domain.interactor.GetApplicantListUseCase;
import by.fro.domain.interactor.SearchApplicantsByNameUseCase;
import by.fro.presentation.di.PerActivity;
import by.fro.presentation.mapper.ApplicantPresentationMapper;
import by.fro.presentation.model.ApplicantPresentationModel;
import by.fro.presentation.view.ApplicantsListView;
import io.reactivex.observers.DisposableObserver;

@PerActivity
public class Presenter {

    private ApplicantsListView viewListView;
    private final GetApplicantListUseCase getApplicantListUseCase;
    private final CreateApplicantsUseCase createApplicantsUseCase;
    private final SearchApplicantsByNameUseCase searchApplicantsByNameUseCase;
    private final ApplicantPresentationMapper mapper;
    private Collection<ApplicantPresentationModel> applicantPresentationModels;

    @Inject
    public Presenter(GetApplicantListUseCase getApplicantListUseCase,
                     CreateApplicantsUseCase createApplicantsUseCase,
                     SearchApplicantsByNameUseCase searchApplicantsByNameUseCase,
                     ApplicantPresentationMapper mapper) {
        this.getApplicantListUseCase = getApplicantListUseCase;
        this.createApplicantsUseCase = createApplicantsUseCase;
        this.searchApplicantsByNameUseCase = searchApplicantsByNameUseCase;
        this.mapper = mapper;
    }

    public void setView(@NonNull ApplicantsListView view) {
        this.viewListView = view;
    }

    public void destroy() {
        this.getApplicantListUseCase.dispose();
        this.viewListView = null;
    }


    public void initialize() {
        generateApplicants();
    }

    public void liveSearch(String name) {
        liveSearchApplicants(name);
    }

    public void sortByExperience() {
        Collections.sort((List)applicantPresentationModels,
                (Comparator<ApplicantPresentationModel>) (t, t1) -> Integer.parseInt(t1.getExperienceYears()) - Integer.parseInt(t.getExperienceYears()));
        this.viewListView.showApplicants(applicantPresentationModels);
    }

    private void loadApplicantsList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getApplicantsList();
    }

    private void showViewLoading() {
        this.viewListView.showLoading();
    }

    private void hideViewLoading() {
        this.viewListView.hideLoading();
    }

    private void showViewRetry() {
        this.viewListView.showRetry();
    }

    private void hideViewRetry() {
        this.viewListView.hideRetry();
    }

    private void showErrorMessage(Throwable t) {
        this.viewListView.showError(t.getMessage());
    }

    private void showApplicantsCollectionInView(Collection<Applicant> applicantCollection) {
//        final Collection<ApplicantPresentationModel> applicantPresentationModels =
        applicantPresentationModels = this.mapper.transform(applicantCollection);
        this.viewListView.showApplicants(applicantPresentationModels);
    }

    private void getApplicantsList() {
        this.getApplicantListUseCase.execute(new ApplicantListObserver(), null);
    }

    private void generateApplicants() {
        this.createApplicantsUseCase.execute(new GeneratedApplicantsListObserver(), null);
    }

    private void liveSearchApplicants(String name) {
        this.searchApplicantsByNameUseCase.execute(new ApplicantListObserver(), name);
    }

    private final class ApplicantListObserver extends DisposableObserver<List<Applicant>> {

        @Override public void onComplete() {
            Presenter.this.hideViewLoading();
        }

        @Override public void onError(Throwable e) {
            Presenter.this.hideViewLoading();
            Presenter.this.showErrorMessage(e);
            Presenter.this.showViewRetry();
        }

        @Override public void onNext(List<Applicant> applicants) {
            Presenter.this.showApplicantsCollectionInView(applicants);
        }
    }

    private final class GeneratedApplicantsListObserver extends DisposableObserver<Long> {

        @Override
        public void onNext(Long applicant) {
            if (BuildConfig.DEBUG) {
                Log.d("Applicant row: ", applicant.toString());
            }
        }

        @Override
        public void onError(Throwable e) {
            Presenter.this.hideViewLoading();
            Presenter.this.showErrorMessage(e);
            Presenter.this.showViewRetry();
        }

        @Override
        public void onComplete() {
            loadApplicantsList();
        }
    }
}
