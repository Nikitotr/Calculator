package com.example.calculatorfinal1.domain.usecases

import com.example.calculatorfinal1.domain.usecases.datasource.ICalculatorDataSource

class SaveCalculatorHistoryUseCase(private val dataSource: ICalculatorDataSource) {
    suspend operator fun invoke(expression: String): Result<Int> {
        return dataSource.saveCalulatorHistory(expression)
    }
}
