package com.example.telassessment.network;

import android.arch.lifecycle.MutableLiveData;

import com.example.telassessment.app.AppController;
import com.example.telassessment.model.DataModel;
import com.example.telassessment.model.DataRepoModel;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    @Inject
    RetrofitAPIInterface webService;

    private static MutableLiveData<DataRepoModel> data = new MutableLiveData<>();

    public DataRepository() {
    }

    public MutableLiveData<DataRepoModel> getList() {

        AppController.getAppComponent().inject(this);

        Call<DataModel> call = webService.retrieveList();
        call.enqueue(new Callback<DataModel>() {

            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                data.setValue(new DataRepoModel(response.body()));
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                data.setValue(new DataRepoModel(t));
            }
        });

        return data;
    }
}
