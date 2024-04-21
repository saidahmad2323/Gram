package com.example.gramm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomBars(
    bottomSheetState: BottomSheetState,
    onClick: () -> Unit
) {
    BottomAppBar(
        cutoutShape = CircleShape,
        backgroundColor = Color.White,
        elevation = 0.dp,
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.height(95.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = {

            }) {
                Image(painter = painterResource(id = R.drawable.iconmoney)
                    , contentDescription = null, Modifier.size(35.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 0.dp)
                    .height(90.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                shape = RoundedCornerShape(15.dp),
                onClick = {

                }
            ) {
                Text(
                    text = "Заказать",
                    color = Color.White,
                    fontSize = 19.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                onClick()
            }) {
                Icon(painter = painterResource(id = if (bottomSheetState.isExpanded) R.drawable.ic_baseline_keyboard_arrow_down_24 else R.drawable.ic_baseline_more_horiz_24)
                    , contentDescription = null)
            }
        }

    }
}