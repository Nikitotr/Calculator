package com.example.calculatorfinal1.domain.usecases.datasource

interface ICalculatorDataSource {
    suspend fun saveCalculatorHistory(expression : String): Result<Int>
    suspend fun getCalculatorHistory(): Result<List<String>>
    suspend fun clearCalculatorHistory(): Result<Int>
}