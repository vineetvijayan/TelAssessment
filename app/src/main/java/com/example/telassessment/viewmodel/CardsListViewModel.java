package com.example.telassessment.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.telassessment.model.DataModel;
import com.example.telassessment.network.DataRepository;

import java.util.List;

public class CardsListViewModel extends AndroidViewModel {

    private final LiveData<DataModel> listObservable;

    public CardsListViewModel(@NonNull Application application) {
        super(application);
        this.listObservable = DataRepository.getList();
    }

    public LiveData<DataModel> getListObservable() {
        return listObservable;
    }
}
