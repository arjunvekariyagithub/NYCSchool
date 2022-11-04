package com.test.nycschools.data.interactor

class GenerateDataPoints : SuspendableUseCase<String, Boolean> {
    override suspend fun execute(params: String): Boolean {
        return params.isNotEmpty()
    }
}