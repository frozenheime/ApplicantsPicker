package by.fro.presentation;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collection;
import javax.inject.Inject;
import by.fro.core.R;
import by.fro.presentation.di.HasComponent;
import by.fro.presentation.di.components.ApplicantComponent;
import by.fro.presentation.di.components.DaggerApplicantComponent;
import by.fro.presentation.model.ApplicantPresentationModel;
import by.fro.presentation.view.ApplicantsListView;


public class MainActivity extends BaseActivity implements ApplicantsListView, HasComponent<ApplicantComponent> {

    @Inject
    Presenter presenter;
    @Inject
    ApplicantAdapter applicantAdapter;

    private RecyclerView recyclerView;
    private RelativeLayout rl_progress;
    private RelativeLayout rl_retry;
    private ApplicantComponent applicantComponent;
    private SearchView searchView;
    private AppCompatButton sortButton;
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_applicants);
        rl_progress = findViewById(R.id.rl_progress);
        rl_retry = findViewById(R.id.rl_retry);
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        this.initializeInjector();
        applicantComponent.inject(this);
        this.presenter.setView(this);
        setupRecyclerView();
        this.presenter.initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
//        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(onQueryTextListener);

        sortButton = (AppCompatButton)menu.findItem(R.id.action_sort).getActionView();
        sortButton.setOnClickListener(searchButtonOnClickListener);

        return super.onCreateOptionsMenu(menu);
    }

    private AppCompatButton.OnClickListener searchButtonOnClickListener =
            view -> presenter.sortByExperience();

    private SearchView.OnQueryTextListener onQueryTextListener =
            new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    liveSearch(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    liveSearch(newText);
                    return true;
                }

                private void liveSearch(String searchText) {
                    searchText = "%"+searchText+"%";
                    presenter.liveSearch(searchText);
                }
            };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView = null;
        presenter.destroy();
    }

    @Override
    public void showApplicants(Collection<ApplicantPresentationModel> applicantPresentationModels) {
        if (applicantPresentationModels != null) {
            this.applicantAdapter.setApplicantsCollection(applicantPresentationModels);
        }
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Log.e("APPLICANT ERROR", message);
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void setupRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(context()));
        this.recyclerView.setAdapter(applicantAdapter);
    }

    private void initializeInjector() {
        this.applicantComponent = DaggerApplicantComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public ApplicantComponent getComponent() {
        return applicantComponent;
    }
}
