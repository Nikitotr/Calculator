package com.example.calculatorfinal1.persistence.repository

import com.example.calculatorfinal1.data.mapEntitiesToModels
import com.example.calculatorfinal1.domain.historyItem.HistoryModel
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

    override suspend fun getCalculatorHistory(): Result<List<HistoryModel>> {
        val result = try {
            val history = dao.getHistory()
            val models = mapEntitiesToModels(history)
            if (models.isNotEmpty()) {
                Result.success(models)
            } else {
                Result.success(listOf())
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        return result
    }

    override suspend fun deleteHistoryItem(id: Int): Result<Int> {
        return  try {
            val deletedRows = dao.deleteById(id)
            Result.success(deletedRows)
        } catch (e: Exception) {
            Result.failure(e)
    }
}
}
