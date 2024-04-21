package com.example.gramm.Layout.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun BottomSheets(
    color: Color = Color.Transparent,
    titles: String,
    placeholder: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    bottomSheetState: BottomSheetState,
    state: Boolean = false
) {
    val padding = remember {
        mutableStateOf(0.62)
    }
    var title by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    if (bottomSheetState.isExpanded) {
        keyboardController?.show()
        focusRequester.requestFocus()
        padding.value = 0.62
    } else {
        keyboardController?.hide()
        padding.value = 1.0
    }

    var buttonTopMargin by remember { mutableStateOf(0.dp) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.94f)
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
        Box(
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                placeholder = { Text(text = placeholder) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .onFocusChanged { focusState ->
                        if (focusState.isFocused) {
                            // Устанавливаем отступ, когда TextField получает фокус
                            buttonTopMargin = (-5).dp
                        } else {
                            // Сбрасываем отступ, когда TextField теряет фокус
                            buttonTopMargin = 0.dp
                        }
                    }
                    .focusRequester(focusRequester),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = color,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.Black
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send,
                    keyboardType = keyboardOptions.keyboardType
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        // Ваша логика при отправке текста
                    }
                ),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight(padding.value.toFloat())
                .padding(bottom = 10.dp), contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                shape = RoundedCornerShape(15.dp),
                onClick = {
                    coroutineScope.launch {
                        bottomSheetState.collapse()
                    }
                }
            ) {
                Text(
                    text = "Готов",
                    color = Color.White,
                    fontSize = 19.sp
                )
            }
        }

    }
}