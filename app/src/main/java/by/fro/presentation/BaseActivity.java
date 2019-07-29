package by.fro.presentation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import by.fro.presentation.di.components.ApplicationComponent;
import by.fro.presentation.di.modules.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
