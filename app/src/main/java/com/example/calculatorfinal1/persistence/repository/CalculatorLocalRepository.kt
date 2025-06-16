package com.example.calculatorfinal1.persistence.repository

import com.example.calculatorfinal1.persistence.dao.CalculatorDao
import com.example.calculatorfinal1.persistence.entity.CalculatorHistoryEntity

class CalculatorLocalRepository(private val dao: CalculatorDao) : ICalculatorLocalRepository {
    override suspend fun saveCalculatorHistory(entity: CalculatorHistoryEntity): Result<Int> {
        return try {
            dao.clearHistory()
            val rowId = dao.insert(entity)
            Result.success(rowId.toInt())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCalculatorHistory(): Result<String> {
        return try {
            val latestHistory = dao.getLatestHistory()
            if (latestHistory != null) {
                Result.success(latestHistory.expression)
            } else {
                Result.success("")
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
