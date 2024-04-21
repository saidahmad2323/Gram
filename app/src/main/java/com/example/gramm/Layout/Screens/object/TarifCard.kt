package com.example.gramm.Layout.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramm.Tariff

@Composable
fun TariffCard(
    width: Int = 82,
    tariff: Tariff,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    val backgroundColor = if (isSelected) Color.LightGray else Color.Transparent
    val textColor = if (isSelected) Color.Black else Color.Gray

    Card(
        modifier = Modifier
            .width(width.dp)
            .padding(bottom = 22.dp, top = 15.dp),
        elevation = 0.dp,
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(18.dp)
    ) {
        Box(
            modifier = Modifier
                .width(87.dp)
                .clickable { onSelected() }
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(6.dp)
            ) {
                Image(
                    painter = painterResource(id = tariff.icon),
                    contentDescription = null,
                    modifier = Modifier.size(45.dp)
                )
                Text(
                    text = tariff.title,
                    fontSize = 13.sp,
                    color = textColor
                )
                Text(
                    text = "${tariff.price} с",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            }
        }

    }
}