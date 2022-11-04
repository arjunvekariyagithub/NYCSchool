package com.test.nycschools.data.interactor;

import io.reactivex.rxjava3.core.Flowable;

/**
 * An interface representing an executable operation that returns a [Flowable].
 */
interface FlowableUseCase<T, R> extends UseCase<T, Flowable<R>> {

}
