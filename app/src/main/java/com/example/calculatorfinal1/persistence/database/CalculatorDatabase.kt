package com.example.calculatorfinal1.persistence.database

import androidx.room.Room
import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculatorfinal1.persistence.dao.CalculatorDao
import com.example.calculatorfinal1.persistence.entity.CalculatorHistoryEntity

private const val version = 1

@Database(
    version = version,
    entities = [CalculatorHistoryEntity::class],
    autoMigrations = []
)

abstract class CalculatorDatabase : RoomDatabase() {
    abstract fun calculatorDao(): CalculatorDao

    companion object {
        @Volatile
        private var INSTANCE: CalculatorDatabase? = null

        fun getInstance(context: Context): CalculatorDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): CalculatorDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                CalculatorDatabase::class.java,
                "CalculatorDatabase.db"
            )
                .fallbackToDestructiveMigration(dropAllTables = true)
                .build()
        }
    }
}