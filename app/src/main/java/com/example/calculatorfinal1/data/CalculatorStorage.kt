
package com.example.calculatorfinal1.data

import com.example.calculatorfinal1.domain.usecases.datasource.ICalculatorDataSource
import com.example.calculatorfinal1.persistence.repository.ICalculatorLocalRepository

class CalculatorStorage(
    private val localRepository: ICalculatorLocalRepository
) : ICalculatorDataSource {

    override suspend fun saveCalculatorHistory(expression: String): Result<Int> {
        val entity = toEntity(expression)
        return localRepository.saveCalculatorHistory(entity)
    }

    override suspend fun getCalculatorHistory(): Result<List<String>> {
        return localRepository.getCalculatorHistory()
    }

    override suspend fun clearCalculatorHistory(): Result<Int> {
        return localRepository.clearCalculatorHistory()
    }
}
