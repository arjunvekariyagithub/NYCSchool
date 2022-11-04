package com.test.nycschools.data;

import com.test.nycschools.data.dispatchers.AppSchedulers;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AppSchedulersImpl implements AppSchedulers {

    @Inject
    public AppSchedulersImpl() {
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler networkIo() {
        return Schedulers.io();
    }

    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }
}
