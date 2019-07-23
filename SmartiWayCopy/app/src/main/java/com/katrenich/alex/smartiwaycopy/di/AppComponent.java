package com.katrenich.alex.smartiwaycopy.di;

import com.katrenich.alex.smartiwaycopy.utils.AppModule;

import javax.inject.Singleton;


import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

}
