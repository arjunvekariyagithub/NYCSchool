package com.test.nycschools.data.interactor;

import com.test.nycschools.data.model.SchoolDetails;
import com.test.nycschools.data.repository.SchoolRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetSchoolDetails implements SingleUseCase<GetSchoolDetails.Params, SchoolDetails> {

    public SchoolRepository repository;

    @Inject
    public GetSchoolDetails(SchoolRepository repository) {
        this.repository = repository;
    }

    @Override
    public Single<SchoolDetails> execute(Params params) {
        return repository.getSchoolDetails(params.dbn);

    }

    public static class Params {
        String dbn;

        public Params(String dbn) {
            this.dbn = dbn;
        }
    }
}
