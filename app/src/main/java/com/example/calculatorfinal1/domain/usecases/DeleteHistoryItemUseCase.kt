package com.example.calculatorfinal1.domain.usecases

import com.example.calculatorfinal1.domain.usecases.datasource.ICalculatorDataSource

class DeleteHistoryItemUseCase (private val dataSource: ICalculatorDataSource){
    suspend operator fun invoke(id: Int): Result<Int>{
        return dataSource.deleteHistoryItem(id)
    }
}
