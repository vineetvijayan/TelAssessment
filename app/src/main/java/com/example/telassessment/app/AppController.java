package com.example.telassessment.app;

import android.app.Application;

import com.example.telassessment.di.AppComponent;
import com.example.telassessment.di.AppModule;
import com.example.telassessment.di.DaggerAppComponent;

public class AppController extends Application {
    private static AppComponent appComponent;

    private static AppController get() {
        return (AppController) appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildAppComponent();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static AppComponent buildAppComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule()).build();
    }
}