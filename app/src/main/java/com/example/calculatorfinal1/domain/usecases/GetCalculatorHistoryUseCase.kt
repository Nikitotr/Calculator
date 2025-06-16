package com.example.calculatorfinal1.domain.usecases

import com.example.calculatorfinal1.domain.usecases.datasource.ICalculatorDataSource

class GetCalculatorHistoryUseCase(private val dataSource: ICalculatorDataSource) {
    suspend operator fun invoke(): Result<String> {
        return dataSource.getCalculatorHistory()
    }
}
