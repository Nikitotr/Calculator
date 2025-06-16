package com.example.calculatorfinal1.persistence.repository

import com.example.calculatorfinal1.persistence.entity.CalculatorHistoryEntity


interface ICalculatorLocalRepository {
    suspend fun saveCalculatorHistory(entity : CalculatorHistoryEntity): Result<Int>
    suspend fun getCalculatorHistory(): Result<String>
}
