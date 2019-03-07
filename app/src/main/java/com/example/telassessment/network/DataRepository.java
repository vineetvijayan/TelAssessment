package com.example.telassessment.network;

import android.arch.lifecycle.MutableLiveData;

import com.example.telassessment.app.AppController;
import com.example.telassessment.model.DataModel;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    @Inject
    RetrofitAPIInterface webService;

    private static final MutableLiveData<DataModel> data = new MutableLiveData<>();

    public DataRepository() {
        AppController.getAppComponent().inject(this);
    }

    public MutableLiveData<DataModel> getList() {

        Call<DataModel> call = webService.retrieveList();
        call.enqueue(new Callback<DataModel>() {

            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
