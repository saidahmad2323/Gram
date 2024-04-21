package com.example.gramm.Layout.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonType(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color.LightGray else Color.Transparent
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 0.dp,
        backgroundColor = backgroundColor
    ) {
        Box(
            modifier = Modifier.clickable {
                onClick()
            }
        ) {
            Text(text = title, modifier = Modifier.padding(10.dp))
        }

    }
}