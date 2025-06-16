package com.example.calculatorfinal1.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculatorfinal1.domain.usecases.GetCalculatorHistoryUseCase
import com.example.calculatorfinal1.domain.usecases.SaveCalculatorHistoryUseCase
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject


class CalculatorViewModel(
    private val saveCalculatorHistoryUseCase: SaveCalculatorHistoryUseCase,
   private val getCalculatorHistoryUseCase: GetCalculatorHistoryUseCase
): ViewModel(), KoinComponent {

    var resultText by mutableStateOf("0")
    private var resultCache: String = "0"
    var memoryValue by mutableStateOf("")

    fun onButtonClick(btn: String) {
        resultText.let { value ->
            when (btn) {
                "AC" -> {
                    memoryValue = ""
                    resultText = "0"
                    resultCache = "0"
                }

                "C" -> {
                    memoryValue = ""
                    resultText =
                        if (value.isEmpty()) "0"
                        else
                            value.substring(0, value.length - 1)
                }

                "=" -> {
                    memoryValue = ""
                    try {
                        resultText = calculateResult(value)
                        resultCache = resultText
                    } catch (e: Exception) {
                        resultText = "Error"
                        resultCache = "0"
                    }
                }

                "SAVE" -> {
                    saveHistory()
                }

                "GET" -> {
                    getHistory()
                }

                else -> {
                    memoryValue = ""
                    if (resultText == "0") {
                        resultText = btn
                    } else if (resultCache != "0" && btn in "+-/*=)(") {
                        resultCache = "0"
                        resultText += btn

                    } else if (resultCache != "0" && btn in "0123456789") {
                        resultText = btn
                        resultCache += btn

                    } else {
                        resultText += btn
                    }
                }
            }
        }
    }

     private fun saveHistory() {
        viewModelScope.launch {
            saveCalculatorHistoryUseCase(resultText).onSuccess {
                memoryValue = "SAVED: $resultText"
            }.onFailure {
                memoryValue = "SAVE ERROR"
            }
        }
    }

     private fun getHistory() {
        viewModelScope.launch {
            getCalculatorHistoryUseCase().onSuccess { data ->
                memoryValue = "MEMORY: $data"
                resultText = data
                resultCache = data
            }.onFailure {
                memoryValue = "DOWNLOAD ERROR"
            }
        }
    }

    private fun calculateResult(equation: String): String {
        return try {
            val expression = ExpressionBuilder(equation).build()
            val result = expression.evaluate()
            if (result % 1 == 0.0) {
                result.toInt().toString()
            } else {
                "%.6f".format(result).replace(",", ".")
            }
        } catch (e: Exception) {
            "Error"
        }
    }
}

