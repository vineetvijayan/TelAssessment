package com.example.telassessment.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;

import com.example.telassessment.model.DataModel;
import com.example.telassessment.network.DataRepository;
import com.example.telassessment.network.RetrofitAPIInterface;

import org.junit.After;
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

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CardsListViewModelTest {

    @InjectMocks
    CardsListViewModel viewModel;

    @Mock
    DataRepository repository;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    MockWebServer mockWebServer;
    Retrofit retrofit;
    RetrofitAPIInterface service;
    DataModel dataModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockWebServer = new MockWebServer();
        retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RetrofitAPIInterface.class);

        viewModel = org.mockito.Mockito.mock(CardsListViewModel.class);
    }

    @Test
    public void fetchList() {

        dataModel = Mockito.mock(DataModel.class);
        dataModel.setTitle("abc");

        MutableLiveData<DataModel> data = new MutableLiveData<>();
        data.postValue(dataModel);

        //Setting how up the mock behaves
        Mockito.doReturn(data).when(viewModel).getListObservable();
//
        repository.getList();
        Mockito.verify(repository).getList();

        Assert.assertEquals(data, viewModel.getListObservable());
    }

    @Test
    public void testApiSuccess() throws IOException {

        mockWebServer.enqueue(new MockResponse().setBody("{\n" +
                "  \"title\": \"About Canada\",\n" +
                "  \"rows\": [\n" +
                "    {\n" +
                "      \"title\": \"Beavers\",\n" +
                "      \"description\": \"Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony\",\n" +
                "      \"imageHref\": \"http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg\"\n" +
                "    }\n" +
                "  ]\n" +
                "}"));

        Call<DataModel> call = service.retrieveList();
        Response<DataModel> dataModel = call.execute();

        assertTrue(dataModel != null);
        assertEquals("Beavers", dataModel.body().getRows().get(0).getTitle());
        assertEquals("About Canada", dataModel.body().getTitle());
    }

    @Test
    public void testApiFailure() throws IOException {

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody(""));

        Call<DataModel> call = service.retrieveList();
        Response<DataModel> dataModel = call.execute();

        assertNull(dataModel.body());
    }

    @After
    public void tearDown() {
        //Finish web server
        try {
            mockWebServer.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
