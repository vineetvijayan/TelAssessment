package com.example.telassessment.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.example.telassessment.app.AppController;
import com.example.telassessment.model.DataModel;
import com.example.telassessment.network.DataRepository;

import javax.inject.Inject;

public class CardsListViewModel extends ViewModel {

    private LiveData<DataModel> listObservable;
    public final ObservableBoolean isLoading = new ObservableBoolean();
    public final ObservableBoolean isError = new ObservableBoolean();

    @Inject
    DataRepository repository;

    public CardsListViewModel() {
        super();
    }

    public void fetchList() {
        isLoading.set(true);
        AppController.getAppComponent().inject(this);
        setListObservable(repository.getList());
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
