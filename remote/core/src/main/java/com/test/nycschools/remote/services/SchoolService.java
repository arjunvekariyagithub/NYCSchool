package com.test.nycschools.remote.services;

import com.test.nycschools.remote.model.SchoolDetailsResponse;
import com.test.nycschools.remote.model.SchoolResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SchoolService {

    @GET("s3k6-pzi2.json")
    Single<List<SchoolResponse>> getSchools(@Query("$offset") int offset, @Query("$limit") int limit);

    @GET("f9bf-2cp4.json")
    Single<List<SchoolDetailsResponse>> getSchoolDetails(@Query("dbn") String dbn);

}
