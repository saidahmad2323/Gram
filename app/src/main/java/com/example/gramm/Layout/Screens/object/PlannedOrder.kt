package com.example.gramm.Layout.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gramm.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun PlannedOrder(
    titles: String,
    bottomSheetState: BottomSheetState,
) {
    var title by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(text = titles, modifier = Modifier.padding(20.dp))
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch {
                        bottomSheetState.collapse()
                    }
                }, elevation = 0.dp
        ) {
            Text("Через 10 минут", Modifier.padding(20.dp))
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch {
                        bottomSheetState.collapse()
                    }
                }, elevation = 0.dp
        ) {
            Text("Через 15 минут", Modifier.padding(20.dp))
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth(0.89f)
                .padding(top = 5.dp, bottom = 5.dp)
                .align(alignment = Alignment.CenterHorizontally)
                .height(2.dp),
            color = Color.LightGray
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    coroutineScope.launch {
                        bottomSheetState.collapse()

                    }
                }, elevation = 0.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 17.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Указать дату и время", Modifier.padding(20.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios_24),
                    contentDescription = "icon",
                    Modifier.size(16.dp)
                )
            }

        }
    }
}

