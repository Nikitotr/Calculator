package com.example.calculatorfinal1.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorfinal1.R
import com.example.calculatorfinal1.ui.items.HistoryItemUi


@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    result: String,
    memoryValue: String,
    historyList: List<HistoryItemUi>,
    onClick: (String) -> Unit,
    onSave: () -> Unit,
    onGet: () -> Unit,
    onGetDropDownSelected: (String) -> Unit,
    onDropDownDeleteItem: (HistoryItemUi) -> Unit,
    onClear: () -> Unit
) {
    val buttonList = listOf(
        "AC", "(", ")", "/",
        "7", "8", "9", "*",
        "4", "5", "6", "+",
        "1", "2", "3", "-",
        "C", "0", ".", "=",
    )
    var isDropdownExpanded by remember { mutableStateOf(false) }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxHeight()
                .width(450.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = memoryValue,
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 2,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = result, //"viewModel.resultText",
                style = TextStyle(
                    fontSize = 60.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 2,
                modifier = Modifier.padding(16.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                items(buttonList) { btn ->
                    CalculatorButton(btn = btn, onClick = {
                        //viewModel.onButtonClick(btn)
                        onClick(btn)
                    })
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {

                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(horizontal = 4.dp)
                ) {
                    LargeCalculatorButton(
                        btn = "SAVE",
                        onClick = {
                            onSave()
                        }
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                ) {
                    LargeCalculatorButton(
                        btn = "HISTORY",
                        onClick = {
                            isDropdownExpanded = true
                            onGet()
                        }
                    )

                    DropdownMenu(
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false }
                    ) {
                        historyList.forEach { item ->
//                            DropdownMenuItem(
//                                text = { Text(item.value) },
//                                onClick = {
//                                    isDropdownExpanded = false
//                                    onGetDropDownSelected(item.value)
//                                }
//                            )
                            CalculatorButtonClear(
                                historyItemUi = item,
                                onDropDownDeleteItem = {
                                    onDropDownDeleteItem(item)
                                },
                                onDropDownItemClick = {
                                    isDropdownExpanded = false
                                    onGetDropDownSelected(item.value)
                                }
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(horizontal = 4.dp)
                ) {
                    LargeCalculatorButton(
                        btn = "CLEAR",
                        onClick = {
                            onClear()
                        }
                    )
                }

            }
        }
    }


}

@Composable
fun CalculatorButtonClear(
    historyItemUi: HistoryItemUi,
    onDropDownItemClick: () -> Unit,
    onDropDownDeleteItem: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = historyItemUi.value,
            fontSize = 22.sp,
            textAlign = TextAlign.Start,
            maxLines = 1,
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
                .clickable { onDropDownItemClick() }
        )
        Icon(
            painter = painterResource(R.drawable.ic_clear),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .size(36.dp)
                .padding(horizontal = 8.dp, vertical = 6.dp)
                .clickable { onDropDownDeleteItem() } // onDeleteItem()

        )
    }
}

@Composable
fun CalculatorButton(btn: String, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(10.dp)) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier.size(80.dp),
            shape = CutCornerShape(10.dp),
            contentColor = Color.White,
            containerColor = getColor(btn)
        ) {
            Text(text = btn, fontSize = 35.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun LargeCalculatorButton(
    btn: String,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.padding(vertical = 8.dp)) {
        FloatingActionButton(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            shape = CutCornerShape(10.dp),
            contentColor = Color.White,
            containerColor = getColor(btn)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                when (btn) {
                    "SAVE" -> Icon(
                        painter = painterResource(R.drawable.ic_save),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = Color.White
                    )

                    "HISTORY" -> Icon(
                        painter = painterResource(R.drawable.ic_get),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = Color.White
                    )

                    "CLEAR" -> Icon(
                        painter = painterResource(R.drawable.ic_clear),
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = Color.White
                    )
                }

                if (btn == "HISTORY") {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = btn,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}


fun getColor(btn: String): Color {
    if (btn == "C" || btn == "AC")
        return Color(0xFFFFE0B2)
    if (btn == "(" || btn == ")")
        return Color.Gray
    if (btn == "/" || btn == "*" || btn == "+" || btn == "-" || btn == "=")
        return Color(0xFFFF9800)
    if (btn == "SAVE" || btn == "CLEAR" || btn == "HISTORY")
        return Color(0xFF4CAF50)

    return Color(0xFF00C8C9)
}
