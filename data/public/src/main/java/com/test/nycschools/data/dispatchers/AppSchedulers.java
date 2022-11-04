package com.test.nycschools.data.dispatchers;

import io.reactivex.rxjava3.core.Scheduler;

public interface AppSchedulers {
    Scheduler computation();
    Scheduler networkIo();
    Scheduler mainThread();
}
