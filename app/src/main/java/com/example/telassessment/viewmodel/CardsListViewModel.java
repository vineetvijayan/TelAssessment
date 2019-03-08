package com.example.telassessment.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.example.telassessment.app.AppController;
import com.example.telassessment.model.DataModel;
import com.example.telassessment.network.DataRepository;

import javax.inject.Inject;

public class CardsListViewModel extends ViewModel {

    public MutableLiveData<DataModel> listObservable;
    public ObservableBoolean isLoading = new ObservableBoolean();
    public ObservableBoolean isError = new ObservableBoolean();

    @Inject
    DataRepository repository;

    public CardsListViewModel() {
        super();
    }

    public void fetchList() {
        isLoading.set(true);
        AppController.getAppComponent().inject(this);
        listObservable = repository.getList();
    }

    public LiveData<DataModel> getListObservable() {
        return listObservable;
    }
}
