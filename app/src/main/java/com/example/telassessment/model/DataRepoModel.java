package com.example.telassessment.model;

public class DataRepoModel {

    private DataModel dataModel;
    private Throwable mThrowable;

    public DataRepoModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public DataRepoModel(Throwable throwable) {
        this.mThrowable = throwable;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

}
