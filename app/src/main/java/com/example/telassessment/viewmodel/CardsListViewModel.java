package com.example.telassessment.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.example.telassessment.model.DataModel;
import com.example.telassessment.network.DataRepository;

import java.util.List;

public class CardsListViewModel extends AndroidViewModel {

    private LiveData<DataModel> listObservable;
    public ObservableBoolean isLoading = new ObservableBoolean();
    public ObservableBoolean isError = new ObservableBoolean();

    public CardsListViewModel(@NonNull Application application) {
        super(application);
        isLoading.set(true);
        fetchList();
    }

    private void fetchList() {
        this.listObservable = DataRepository.getList();
        isLoading.set(false);
    }

    public LiveData<DataModel> getListObservable() {
        return listObservable;
    }

    public void onRefresh() {
        isLoading.set(true);
        fetchList();
    }
}
