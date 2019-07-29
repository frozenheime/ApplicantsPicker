package by.fro.presentation.di.components;

import by.fro.presentation.MainActivity;
import by.fro.presentation.di.PerActivity;
import by.fro.presentation.di.modules.ActivityModule;
import by.fro.presentation.di.modules.ApplicantModule;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, ApplicantModule.class})
public interface ApplicantComponent extends ActivityComponent {
    void inject(MainActivity mainActivity);
}
