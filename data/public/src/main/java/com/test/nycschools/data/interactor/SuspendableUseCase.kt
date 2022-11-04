package com.test.nycschools.data.interactor

/**
 * An interface representing a suspendable, executable operation.
 */
interface SuspendableUseCase<T, R> {
    suspend fun execute(params: T): R
}

suspend operator fun <T, R> SuspendableUseCase<T, R>.invoke(params: T) = execute(params)

suspend operator fun <R> SuspendableUseCase<Unit, R>.invoke() = execute(Unit)
