package com.example.telassessment.network;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import com.example.telassessment.model.DataModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DataRepositoryTest {

    @Mock
    RetrofitAPIInterface webService;

    @InjectMocks
    DataRepository dataRepository;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dataRepository = Mockito.mock(DataRepository.class);
    }

    @Test
    public void getList() {

        MutableLiveData<DataModel> data = new MutableLiveData<>();

        DataModel dataModel = Mockito.spy(DataModel.class);
        dataModel.setTitle("abc");
        data.setValue(dataModel);

        //Setting how up the mock behaves
        Mockito.doReturn(data).when(dataRepository).getList();

        webService.retrieveList();
        Mockito.verify(webService).retrieveList();

        Assert.assertEquals(data, dataRepository.getList());
    }
}