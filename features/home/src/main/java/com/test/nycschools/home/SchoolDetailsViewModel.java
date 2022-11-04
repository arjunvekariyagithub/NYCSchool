package com.test.nycschools.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.nycschools.data.dispatchers.AppSchedulers;
import com.test.nycschools.data.interactor.GetSchoolDetails;
import com.test.nycschools.data.model.SchoolDetails;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.ResourceSingleObserver;

@HiltViewModel
public class SchoolDetailsViewModel extends ViewModel {

    CompositeDisposable disposable = new CompositeDisposable();
    GetSchoolDetails getSchoolDetails;
    AppSchedulers schedulers;

    private final MutableLiveData<SchoolDetails> _schoolDetails = new MutableLiveData<SchoolDetails>();
    public LiveData<SchoolDetails> schoolDetails = _schoolDetails;

    private final MutableLiveData<Throwable> _error = new MutableLiveData<Throwable>();
    public LiveData<Throwable> error = _error;

    @Inject
    public SchoolDetailsViewModel(GetSchoolDetails getSchoolDetails, AppSchedulers schedulers) {
        this.getSchoolDetails = getSchoolDetails;
        this.schedulers = schedulers;
    }

    protected void getSchoolDetails(String dbn) {
        disposable.add(
                getSchoolDetails.execute(new GetSchoolDetails.Params(dbn))
                        .subscribeOn(schedulers.networkIo())
                        .observeOn(schedulers.mainThread())
                        .subscribeWith(new ResourceSingleObserver<SchoolDetails>() {

                            @Override
                            public void onSuccess(@NonNull SchoolDetails schoolDetails) {
                                _schoolDetails.postValue(schoolDetails);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                _error.postValue(e);
                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}