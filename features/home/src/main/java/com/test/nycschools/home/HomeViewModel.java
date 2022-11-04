package com.test.nycschools.home;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.test.nycschools.data.dispatchers.AppSchedulers;
import com.test.nycschools.data.model.School;
import com.test.nycschools.data.paging.SchoolPagingSource;
import com.test.nycschools.remote.services.SchoolService;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    public Flowable<PagingData<School>> schoolPagingDataFlowable;
    public SchoolService schoolService;
    public AppSchedulers schedulers;

    @Inject
    public HomeViewModel(SchoolService schoolService, AppSchedulers schedulers) {
        this.schoolService = schoolService;
        this.schedulers = schedulers;
        init();
    }

    private void init() {
        // Create new Pager
        Pager<Integer, School> pager = new Pager(
                // Create new paging config
                new PagingConfig(20, //  Count of items in one page
                        20,
                        false,
                        20,
                        20 * 499
                ),
                0,
                () -> new SchoolPagingSource(schoolService, schedulers)
        );

        // inti Flowable
        schoolPagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(schoolPagingDataFlowable, coroutineScope);
    }
}