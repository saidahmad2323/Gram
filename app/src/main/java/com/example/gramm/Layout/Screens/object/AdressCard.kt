package com.example.gramm.Layout.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AdressCard(
    color: Color = Color.Black,
    bottom: Int = 10,
    height: Int = 1,
    title: String,
    icon: Int,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
            verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "add"
                , tint = color
                , modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.padding(horizontal = 10.dp))
                Text(text = title, fontSize = 18.sp)
            }
            content()
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth(0.89f)
                .padding(top = 14.dp, bottom = bottom.dp)
                .align(alignment = Alignment.End)
                .height(height.dp),
            color = Color.LightGray
        )
    }
}