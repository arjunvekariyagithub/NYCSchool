package com.test.nycschools.data.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.test.nycschools.data.dispatchers.AppSchedulers;
import com.test.nycschools.data.model.School;
import com.test.nycschools.remote.services.SchoolService;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class SchoolPagingSource extends RxPagingSource<Integer, School> {

    public static int NETWORK_PAGE_SIZE = 20;

    public SchoolService schoolService;
    public AppSchedulers schedulers;
    public SchoolPagingSource(SchoolService schoolService, AppSchedulers schedulers) {
        this.schoolService = schoolService;
        this.schedulers = schedulers;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, School> pagingState) {
        return null;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, School>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        try {
            int page = loadParams.getKey() != null ? loadParams.getKey() : 0;
            return schoolService.getSchools(
                            page * NETWORK_PAGE_SIZE,
                            NETWORK_PAGE_SIZE
                    ).subscribeOn(schedulers.networkIo())
                    .toObservable()
                    .flatMapIterable(list -> list)
                    .map(it -> new School(it.dbn, it.schoolName, it.schoolAddress, it.schoolPhoneNumber))
                    .toList()
                    .map(schools -> toLoadResult(schools, page))
                    .onErrorReturn(LoadResult.Error::new);
        } catch (Exception e) {
            return Single.just(new LoadResult.Error(e));
        }
    }

    private LoadResult<Integer, School> toLoadResult(List<School> schoolResponses, int page) {
        return new LoadResult.Page(
                schoolResponses,
                page == 0 ? null : page - 1,
                schoolResponses.size() == NETWORK_PAGE_SIZE ? page + 1 : null
        );
    }
}
