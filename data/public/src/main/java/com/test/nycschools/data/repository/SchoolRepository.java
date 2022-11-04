package com.test.nycschools.data.repository;

import com.test.nycschools.data.model.SchoolDetails;

import io.reactivex.rxjava3.core.Single;

public interface SchoolRepository {

    Single<SchoolDetails> getSchoolDetails(String dbn);
}
