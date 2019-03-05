package com.example.telassessment.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import com.example.telassessment.model.DataModel;
import com.example.telassessment.network.DataRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CardsListViewModelTest {

    @Mock
    DataRepository dataRepository;

    @Mock
    CardsListViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }

    @After
    public void tearDown() {
    }

    @Test
    public void fetchList() {

        DataModel dataModel = new DataModel();
        dataModel.setTitle("abc");

        MutableLiveData<DataModel> data = new MutableLiveData<>();
        data.postValue(dataModel);

        //Setting how up the mock behaves
        viewModel = org.mockito.Mockito.spy(CardsListViewModel.class);
        Mockito.doReturn(data).when(viewModel).getListObservable();

        viewModel.fetchList();
        Mockito.verify(viewModel).fetchList();

        Assert.assertEquals(data, viewModel.getListObservable());
    }

    @Test
    public void setListObservable() {

    }

    @Test
    public void onRefresh() {
        fetchList();
    }
}