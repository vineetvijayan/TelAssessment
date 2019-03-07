package com.example.telassessment.di;

import com.example.telassessment.viewmodel.CardsListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {WebServiceModule.class})
@Singleton
public interface AppComponent {
    void inject(CardsListViewModel cardsListViewModel);
}