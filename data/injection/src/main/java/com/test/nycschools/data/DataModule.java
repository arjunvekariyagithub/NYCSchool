package com.test.nycschools.data;

import com.test.nycschools.data.dispatchers.AppSchedulers;
import com.test.nycschools.data.repository.SchoolRepository;
import com.test.nycschools.data.repository.SchoolRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@InstallIn(SingletonComponent.class)
@Module
public abstract class DataModule {

    @Binds
    public abstract AppSchedulers bindDispatchers(AppSchedulersImpl impl);

    @Binds
    abstract SchoolRepository bindSchoolRepository(SchoolRepositoryImpl impl);
}
