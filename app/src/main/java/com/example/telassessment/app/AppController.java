package com.example.telassessment.app;

import android.app.Application;
import android.content.Context;

import com.example.telassessment.di.AppComponent;
import com.example.telassessment.di.DaggerAppComponent;
import com.example.telassessment.di.WebServiceModule;
import com.example.telassessment.network.RetrofitAPIInterface;

public class AppController extends Application {
    private RetrofitAPIInterface retrofitAPIInterface; //retrofit things
    private static AppComponent appComponent;

    private static AppController get(Context context) {
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }

    public void setService(RetrofitAPIInterface retrofitAPIInterface) {
        this.retrofitAPIInterface = retrofitAPIInterface;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildAppComponent();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public AppComponent buildAppComponent() {
        return DaggerAppComponent.builder().webServiceModule(new WebServiceModule()).build();
    }
}