package com.example.calculatorfinal1.persistence.repository

import com.example.calculatorfinal1.data.toExpressions
import com.example.calculatorfinal1.persistence.dao.CalculatorDao
import com.example.calculatorfinal1.persistence.entity.CalculatorHistoryEntity

class CalculatorLocalRepository(private val dao: CalculatorDao) : ICalculatorLocalRepository {
    override suspend fun saveCalculatorHistory(entity: CalculatorHistoryEntity): Result<Int> {
        return try {
            //dao.clearHistory()
            val rowId = dao.insert(entity)
            Result.success(rowId.toInt())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun clearCalculatorHistory(): Result<Int> {
       return try {
          dao.clearHistory()
           Result.success(0)
       }
       catch (e: Exception) {
           Result.failure(e)
       }
    }

    override suspend fun getCalculatorHistory(): Result<List<String>> {
        val result = try {
            val history = dao.getHistory()
            val expressions = history.toExpressions()
            if (expressions.isNotEmpty()) {
                Result.success(expressions)
            } else {
                Result.success(listOf())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        return result
    }
}
