package com.example.telassessment.di;

import com.example.telassessment.network.DataRepository;
import com.example.telassessment.viewmodel.CardsListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(DataRepository dataRepository);
    void inject(CardsListViewModel cardsListViewModel);
}

