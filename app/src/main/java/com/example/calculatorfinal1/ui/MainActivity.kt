package com.example.calculatorfinal1.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
//    private val saveCalculatorHistoryUseCase: SaveCalculatorHistoryUseCase by
//    inject<SaveCalculatorHistoryUseCase>()
//
//    private val getCalculatorHistoryUseCase: GetCalculatorHistoryUseCase by
//    inject<GetCalculatorHistoryUseCase>()

    private val viewModel: CalculatorViewModel by lazy {

       getViewModel<CalculatorViewModel>()

        //GlobalContext.get().get<CalculatorViewModel>()
    }
//        parameters = {
//            parametersOf(
//                saveCalculatorHistoryUseCase,
//                getCalculatorHistoryUseCase
//            )
//
//        }

//     val viewModel: CalculatorViewModel get() = provideViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Calculatorfinal1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Calculator(
                        modifier = Modifier.padding(innerPadding),
                        //viewModel = viewModel
                        onClick = { viewModel.onButtonClick(it) },
                        onSave = { viewModel.onButtonClick("SAVE") },
                        onGet = { viewModel.onButtonClick("GET") },
                        result = viewModel.resultText,
                        memoryValue = viewModel.memoryValue
                    )
                }
            }
        }
    }
//    fun provideViewModel(): CalculatorViewModel = getViewModel<CalculatorViewModel>(
//        parameters = {
//            parametersOf(
//                saveCalculatorHistoryUseCase,
//                getCalculatorHistoryUseCase
//            )
//        }
//    )
}