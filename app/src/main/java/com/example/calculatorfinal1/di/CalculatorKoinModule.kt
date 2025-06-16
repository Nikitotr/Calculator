package com.example.calculatorfinal1.di

import com.example.calculatorfinal1.data.CalculatorStorage
import com.example.calculatorfinal1.domain.usecases.GetCalculatorHistoryUseCase
import com.example.calculatorfinal1.domain.usecases.SaveCalculatorHistoryUseCase
import com.example.calculatorfinal1.domain.usecases.datasource.ICalculatorDataSource
import com.example.calculatorfinal1.persistence.database.CalculatorDatabase
import com.example.calculatorfinal1.persistence.repository.CalculatorLocalRepository
import com.example.calculatorfinal1.persistence.repository.ICalculatorLocalRepository
import com.example.calculatorfinal1.ui.CalculatorViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

object CalculatorKoinModule {
    val AppModule = module {
        single { CalculatorDatabase.getInstance(androidContext()) }
        single { get<CalculatorDatabase>().calculatorDao() }
        single<ICalculatorLocalRepository> { CalculatorLocalRepository(get()) }
        factoryOf(::CalculatorStorage) bind ICalculatorDataSource::class
        factoryOf(::SaveCalculatorHistoryUseCase)
        factoryOf(::GetCalculatorHistoryUseCase)
        viewModelOf(::CalculatorViewModel)
    }
}
