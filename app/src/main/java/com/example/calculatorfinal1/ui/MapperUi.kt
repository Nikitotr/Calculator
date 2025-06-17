package com.example.calculatorfinal1.ui

import com.example.calculatorfinal1.domain.historyItem.HistoryModel
import com.example.calculatorfinal1.ui.items.HistoryItemUi

fun mapModelsToItemUi (models: List <HistoryModel>): List<HistoryItemUi>{
    return models.map {
        HistoryItemUi(it.id, it.value)
    }
}