package com.test.nycschools.remote;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import okhttp3.logging.HttpLoggingInterceptor.Level;

@InstallIn(SingletonComponent.class)
@Module
public class NetworkSettings {
    @Provides
    public Level providesLoggingLevel() {
        return Level.BODY;
    }
}
