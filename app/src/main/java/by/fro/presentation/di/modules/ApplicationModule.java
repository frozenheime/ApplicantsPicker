package by.fro.presentation.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import by.fro.data.dao.ApplicantDao;
import by.fro.data.database.OnDiskDatabase;
import by.fro.data.executor.JobExecutor;
import by.fro.data.repository.ApplicantDataRepository;
import by.fro.data.repository.datasource.HardcodedGodlikeApplicantDatasource;
import by.fro.data.repository.datasource.RandomGeneratorDatasource;
import by.fro.domain.executor.PostExecutionThread;
import by.fro.domain.executor.ThreadExecutor;
import by.fro.domain.repository.ApplicantRepository;
import by.fro.presentation.AndroidApplication;
import by.fro.presentation.UIThread;
import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    ApplicantRepository provideApplicantRepository(ApplicantDataRepository applicantDataRepository) {
        return applicantDataRepository;
    }

    @Provides
    @Singleton
    RandomGeneratorDatasource provideRandomGeneratorDatasource(ApplicantDao dao) {
        return  new RandomGeneratorDatasource(dao, this.application);
    }

    @Provides
    @Singleton
    HardcodedGodlikeApplicantDatasource hardcodedGodlikeApplicantDatasource(ApplicantDao dao) {
        return  new HardcodedGodlikeApplicantDatasource(dao);
    }

    @Provides
    @Singleton
    ApplicantDao provideDao(OnDiskDatabase database) {
        return database.applicantDao();
    }

    @Provides
    @Singleton
    OnDiskDatabase provideDatabase() {
        return OnDiskDatabase.newInstance(this.application);
    }
}
