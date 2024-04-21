package com.example.gramm.Layout.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramm.Tariff

@Composable
fun SelectedTariffInfo(tariff: Tariff) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = tariff.title, fontSize = 35.sp, fontWeight = FontWeight.Bold)
            Text(text = "${tariff.price} смн", fontSize = 30.sp)
        }
        Row(
            modifier = Modifier.fillMaxWidth()
            , horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = tariff.icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth(0.50f)
                    .size(90.dp)
            )
            Text(text = "6 мин", fontSize = 14.sp)
        }
    }
}