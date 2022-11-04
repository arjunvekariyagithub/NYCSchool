package com.test.nycschools.data;

import com.test.nycschools.data.repository.SchoolRepositoryImpl;
import com.test.nycschools.remote.model.SchoolDetailsResponse;
import com.test.nycschools.remote.services.SchoolService;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Single;

public class SchoolRepositoryImplTest {

    private static final String DBN = "dbn";
    private final SchoolDetailsResponse mockResponse = new SchoolDetailsResponse(
            "dbn",
            "name",
            "100",
            "300",
            "325",
            "350"
    );

    private final ArrayList<SchoolDetailsResponse> response = new ArrayList<>(1);
    private final SchoolService mockService = Mockito.mock(SchoolService.class);
    private final SchoolRepositoryImpl subject = new SchoolRepositoryImpl(mockService);

    @Test
    public void verifyIfSchoolDetailsApiReturnsProperResponse() {
        response.add(mockResponse);
        Mockito.when(mockService.getSchoolDetails(DBN)).thenReturn(Single.just(response));

        subject.getSchoolDetails(DBN)
                .test()
                .assertNoErrors()
                .assertValue(it -> it.dbn.equals(mockResponse.dbn));
    }

    @Test
    public void verifySchoolDetailsApiReturnsProperError() {
        response.add(mockResponse);
        Mockito.when(mockService.getSchoolDetails(DBN)).thenReturn(Single.error(new Throwable()));

        subject.getSchoolDetails(DBN)
                .test()
                .assertError(Throwable.class);
    }
}