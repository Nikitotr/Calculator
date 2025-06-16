package com.example.calculatorfinal1.domain.usecases.datasource

interface ICalculatorDataSource {
    suspend fun saveCalulatorHistory(expression : String): Result<Int>
    suspend fun getCalulatorHistory(): Result<String>
}