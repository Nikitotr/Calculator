package com.example.calculatorfinal1.domain.usecases.datasource

import com.example.calculatorfinal1.domain.historyItem.HistoryModel

interface ICalculatorDataSource {
    suspend fun saveCalculatorHistory(expression : String): Result<Int>
    suspend fun getCalculatorHistory(): Result<List<HistoryModel>>
    suspend fun clearCalculatorHistory(): Result<Int>
    suspend fun deleteHistoryItem(id: Int): Result<Int>
}