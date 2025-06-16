
package com.example.calculatorfinal1.data

import com.example.calculatorfinal1.domain.usecases.datasource.ICalculatorDataSource
import com.example.calculatorfinal1.persistence.repository.ICalculatorLocalRepository

class CalculatorStorage(
    //private val localRepository: ICalculatorLocalRepository
) :
    ICalculatorDataSource {

    override suspend fun saveCalulatorHistory(expression: String): Result<Int> {
       // val entity = toEntity(expression)
//        return localRepository.saveCalulatorHistory(entity)

        return Result.success(0)
    }

    override suspend fun getCalulatorHistory(): Result<String> {
//        return localRepository.getCalulatorHistory()
        return Result.success("15")
    }
}
