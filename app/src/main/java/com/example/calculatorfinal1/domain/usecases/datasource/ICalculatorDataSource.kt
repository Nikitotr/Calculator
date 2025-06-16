package com.example.calculatorfinal1.domain.usecases.datasource

interface ICalculatorDataSource {
    suspend fun saveCalculatorHistory(expression : String): Result<Int>
    suspend fun getCalculatorHistory(): Result<String>
}