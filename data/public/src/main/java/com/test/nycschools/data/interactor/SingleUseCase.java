package com.test.nycschools.data.interactor;

import io.reactivex.rxjava3.core.Single;

/**
 * An interface representing an executable operation that returns a [Single].
 */
interface SingleUseCase<T, R> extends UseCase<T, Single<R>> {

}
