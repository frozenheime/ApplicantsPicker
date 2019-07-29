package by.fro.presentation.view;

import java.util.Collection;

import by.fro.presentation.model.ApplicantPresentationModel;

public interface ApplicantsListView extends LoadDataView {
    void showApplicants(Collection<ApplicantPresentationModel> userModelCollection);
}
