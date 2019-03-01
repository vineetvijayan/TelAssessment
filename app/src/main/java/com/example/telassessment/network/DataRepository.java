package com.example.telassessment.network;


import android.arch.lifecycle.MutableLiveData;

import com.example.telassessment.model.DataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    static MutableLiveData<DataModel> data = new MutableLiveData<>();


    public static MutableLiveData<DataModel> getList() {
        RetrofitAPIInterface retrofitApiInterface = RetrofitAPIClient.getRetrofitClient().create(RetrofitAPIInterface.class);

        Call<DataModel> call = retrofitApiInterface.retrieveList();
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
