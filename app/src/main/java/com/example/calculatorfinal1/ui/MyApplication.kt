package com.example.calculatorfinal1.ui

import android.app.Application
import com.example.calculatorfinal1.di.CalculatorKoinModule
import com.example.calculatorfinal1.di.CalculatorKoinModule.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(AppModule)
        }
    }
}