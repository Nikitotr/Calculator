package com.example.calculatorfinal1.data

import com.example.calculatorfinal1.domain.historyItem.HistoryModel
import com.example.calculatorfinal1.persistence.entity.CalculatorHistoryEntity

internal fun toEntity(expression: String): CalculatorHistoryEntity {
    return CalculatorHistoryEntity(expression = expression)
}
 fun List<CalculatorHistoryEntity?>.toExpressions(): List<String> = map { it?.toExpression().orEmpty() }
internal fun CalculatorHistoryEntity.toExpression(): String = this.expression

 fun mapEntitiesToModels(entities: List<CalculatorHistoryEntity>): List<HistoryModel> {
    return entities.map {entity ->
        HistoryModel(entity.id.toInt(), entity.expression)
    }
}


