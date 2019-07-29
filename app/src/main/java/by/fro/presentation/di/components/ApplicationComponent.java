package by.fro.presentation.di.components;

import android.content.Context;

import javax.inject.Singleton;

import by.fro.data.dao.ApplicantDao;
import by.fro.data.database.OnDiskDatabase;
import by.fro.data.repository.datasource.HardcodedGodlikeApplicantDatasource;
import by.fro.data.repository.datasource.RandomGeneratorDatasource;
import by.fro.domain.executor.PostExecutionThread;
import by.fro.domain.executor.ThreadExecutor;
import by.fro.domain.repository.ApplicantRepository;
import by.fro.presentation.BaseActivity;
import by.fro.presentation.di.modules.ApplicationModule;
import dagger.Component;


@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    ApplicantRepository applicantRepository();
    RandomGeneratorDatasource generator();
    HardcodedGodlikeApplicantDatasource godlikeDatasource();
    OnDiskDatabase provideDatabase();
    ApplicantDao dao();
}
