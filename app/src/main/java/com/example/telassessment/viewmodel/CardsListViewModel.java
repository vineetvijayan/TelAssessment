package com.example.telassessment.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.example.telassessment.model.DataModel;
import com.example.telassessment.network.DataRepository;

public class CardsListViewModel extends ViewModel {

    private LiveData<DataModel> listObservable;
    public final ObservableBoolean isLoading = new ObservableBoolean();
    public final ObservableBoolean isError = new ObservableBoolean();

    public CardsListViewModel() {
        super();
        fetchList();
    }

    public void fetchList() {
        isLoading.set(true);
        setListObservable(new DataRepository().getList());
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
