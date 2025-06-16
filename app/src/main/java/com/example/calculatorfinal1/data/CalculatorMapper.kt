package com.example.calculatorfinal1.data

import com.example.calculatorfinal1.persistence.entity.CalculatorHistoryEntity

internal fun toEntity(expression: String): CalculatorHistoryEntity {
    return CalculatorHistoryEntity(expression = expression)
}

internal fun CalculatorHistoryEntity.toExpression(): String = this.expression

