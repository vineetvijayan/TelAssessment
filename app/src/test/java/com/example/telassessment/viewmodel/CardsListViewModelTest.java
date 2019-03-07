package com.example.telassessment.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import com.example.telassessment.model.DataModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CardsListViewModelTest {

    @Mock
    CardsListViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchList() {

        DataModel dataModel = new DataModel();
        dataModel.setTitle("abc");

        MutableLiveData<DataModel> data = new MutableLiveData<>();
        data.postValue(dataModel);

        //Setting how up the mock behaves
//        viewModel = new CardsListViewModel();
        viewModel = org.mockito.Mockito.mock(CardsListViewModel.class);
        Mockito.doReturn(data).when(viewModel).getListObservable();
//
        viewModel.fetchList();
        Mockito.verify(viewModel).fetchList();

        Assert.assertEquals(data, viewModel.getListObservable());
    }

    @Test
    public void onRefresh() {
        fetchList();
    }
}