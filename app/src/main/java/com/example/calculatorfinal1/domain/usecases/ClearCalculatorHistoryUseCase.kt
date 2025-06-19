package com.example.calculatorfinal1.domain.usecases

import com.example.calculatorfinal1.domain.usecases.datasource.ICalculatorDataSource

class ClearCalculatorHistoryUseCase (private val dataSource: ICalculatorDataSource) {
    suspend operator fun invoke(): Result<Int> {
        return dataSource.clearCalculatorHistory()
    }
}
