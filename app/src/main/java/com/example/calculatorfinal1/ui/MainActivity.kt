package com.example.calculatorfinal1.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.calculatorfinal1.domain.usecases.GetCalculatorHistoryUseCase
import com.example.calculatorfinal1.domain.usecases.SaveCalculatorHistoryUseCase
import com.example.calculatorfinal1.ui.theme.Calculatorfinal1Theme
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.parametersOf

class MainActivity : ComponentActivity(), KoinComponent {

    private val viewModel: CalculatorViewModel by lazy {
        getViewModel<CalculatorViewModel>()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Calculatorfinal1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Calculator(
                        modifier = Modifier.padding(innerPadding),
                        onClick = { viewModel.onButtonClick(it) },
                        onSave = { viewModel.onButtonClick("SAVE") },
                        onGet = { viewModel.onButtonClick("GET") },
                        result = viewModel.resultText,
                        memoryValue = viewModel.memoryValue,
                        historyList = viewModel.historyList,
                        onGetDropDownSelected = { viewModel.getDropDownSelected(it) },
                        onClear = {viewModel.clearHistory()}

                    )
                }
            }
        }
    }
}