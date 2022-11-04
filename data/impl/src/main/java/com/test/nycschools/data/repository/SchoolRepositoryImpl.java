package com.test.nycschools.data.repository;

import com.test.nycschools.data.model.SchoolDetails;
import com.test.nycschools.remote.model.SchoolDetailsResponse;
import com.test.nycschools.remote.services.SchoolService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class SchoolRepositoryImpl implements SchoolRepository {

    public SchoolService schoolService;

    @Inject
    public SchoolRepositoryImpl(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @Override
    public Single<SchoolDetails> getSchoolDetails(String dbn) {
        return schoolService.getSchoolDetails(dbn)
                .map(it -> mapDetails(it.get(0)));
    }

    SchoolDetails mapDetails(SchoolDetailsResponse response)  {
        /* Given more time I would prefer to create mapper class for
           SchoolDetailsResponse -> SchoolDetails mapping
        */
        return new SchoolDetails(
                response.dbn,
                response.schoolName,
                response.numberOfTestTakers,
                response.criticalReadingAvgScore,
                response.mathAvgScore,
                response.writingAvgScore
        );
    }
}
