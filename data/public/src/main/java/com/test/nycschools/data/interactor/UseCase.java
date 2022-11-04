package com.test.nycschools.data.interactor;

/**
 * An interface representing a synchronous executable operation.
 */
interface UseCase<T, R> {
    R execute(T params);
}
