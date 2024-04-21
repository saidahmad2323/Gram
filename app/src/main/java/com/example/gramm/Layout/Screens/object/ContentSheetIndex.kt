package com.example.gramm.Layout.Screens

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContentByIndex(
    index: Int,
    bottomSheetScaffoldStates: BottomSheetScaffoldState,

    ) {
    when (index) {
        0 -> {
            BottomSheets(
                titles = "Коментарий водителю",
                placeholder = "Коментарий водителю",
                bottomSheetState = bottomSheetScaffoldStates.bottomSheetState
            )
        }
        1 -> {
            BottomSheets(
                color = Color.Black,
                titles = "Кто поедет на такси?",
                placeholder = "Введите номер телефона",
                bottomSheetState = bottomSheetScaffoldStates.bottomSheetState,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )
        }
        2 -> {
            PlannedOrder(
                titles = "Запланировать поездку",
                bottomSheetState = bottomSheetScaffoldStates.bottomSheetState)
        }
        3 -> BottomSheetsss(
            titles = "Дата и время поездки",
            bottomSheetState = bottomSheetScaffoldStates.bottomSheetState)
    }
}