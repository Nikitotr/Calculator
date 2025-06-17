package com.example.calculatorfinal1.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.calculatorfinal1.persistence.entity.CalculatorHistoryEntity

@Dao
interface CalculatorDao {
    @Insert
    suspend fun insert(history: CalculatorHistoryEntity): Long

    @Query("SELECT * FROM CalculatorDatabase ORDER BY id DESC")
    suspend fun getHistory(): List<CalculatorHistoryEntity>

    @Query("DELETE FROM CalculatorDatabase")
    suspend fun clearHistory(): Int

    @Query("DELETE FROM CalculatorDatabase WHERE id = :id ")
    suspend fun deleteById(id: Int): Int

}

