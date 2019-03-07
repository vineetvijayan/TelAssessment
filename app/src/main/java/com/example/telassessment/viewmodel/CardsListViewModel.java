package com.example.telassessment.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.example.telassessment.app.AppController;
import com.example.telassessment.model.DataModel;
import com.example.telassessment.network.DataRepository;
import com.example.telassessment.network.RetrofitAPIInterface;

import javax.inject.Inject;

public class CardsListViewModel extends ViewModel {

    private LiveData<DataModel> listObservable;
    public final ObservableBoolean isLoading = new ObservableBoolean();
    public final ObservableBoolean isError = new ObservableBoolean();
    private Context context;

    @Inject
    RetrofitAPIInterface webService;

    public CardsListViewModel(@NonNull Context context) {
        super();
        this.context = context;
        fetchList();
    }

    public void fetchList() {
        isLoading.set(true);

        // inject retrofit object
        final AppController appController = AppController.create(context);
        appController.getAppComponent().inject(this);

        setListObservable(DataRepository.getList(webService));
    }

    public LiveData<DataModel> getListObservable() {
        return listObservable;
    }

    public void setListObservable(LiveData<DataModel> listObservable) {
        this.listObservable = listObservable;
    }

    public void onRefresh() {
        fetchList();
    }
}
