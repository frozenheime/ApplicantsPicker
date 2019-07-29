package by.fro.presentation.di.components;

import android.app.Activity;

import by.fro.presentation.di.PerActivity;
import by.fro.presentation.di.modules.ActivityModule;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {

    Activity activity();
}
