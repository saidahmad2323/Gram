package com.example.gramm.Layout.Screens

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gramm.R
import com.example.gramm.ui.theme.Back
import com.example.gramm.ui.theme.TextFields
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen() {
    val offsetY = remember { Animatable(0f) }
    var textVisible by remember { mutableStateOf(true) }
    var stateLoad by remember { mutableStateOf(false) }
    var imageVisible by remember { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Back)
    ) {
        LaunchedEffect(Unit) {
            delay(1000) // Задержка в 2 секунды
            textVisible = false // После 2 секунд делаем текст невидимым
            offsetY.animateTo(-260f, tween(durationMillis = 800)) {
                // Когда анимация завершена, делаем изображение видимым
                coroutine.launch {
                    delay(900)
                    imageVisible = true
                }
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            BasicText(
                text = "Gram",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 52.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSynthesis = FontSynthesis.Weight
                ),
                modifier = Modifier
                    .offset(y = offsetY.value.dp)
                    .padding(16.dp)
            )
        }
        AnimatedVisibility(visible = imageVisible, enter = fadeIn()) {
            if (imageVisible) {
                    LoginScreen()
            }
        }
    }
}

@Composable
fun LoginScreen() {
    var textStateFlow by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    val items = listOf("+992", "+60", "+465", "+21", "+675")
    val selectedItem = items[selectedIndex]
    var number by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    val stateIcon = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = TextFieldValue(selectedItem),
                onValueChange = {},
                readOnly = true,
                textStyle = TextStyle(fontSize = 13.sp),
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.33f)
                    .height(50.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = Color.White),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.clickable { expanded = true }
                    )
                }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(90.dp)
            ) {
                items.forEachIndexed { index, item ->
                    DropdownMenuItem(onClick = {
                        selectedIndex = index
                        expanded = false
                    }) {
                        Text(text = item)
                    }
                }
            }

            Spacer(modifier = Modifier.width(2.dp))

            OutlinedTextField(
                value = number,
                onValueChange = { number = it },
                maxLines = 1,
                textStyle = TextStyle(fontSize = 13.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        BorderStroke(
                            width = if (isError && number.isEmpty()) 2.dp else 0.dp,
                            color = if (isError && number.isEmpty()) Color.Red else Color.Gray
                        ), shape = RoundedCornerShape(4.dp)
                    ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                placeholder = { Text(text = "Телефон", fontSize = 14.sp) }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(text = "Пароль", fontSize = 14.sp) },
            maxLines = 1,
            textStyle = TextStyle(fontSize = 13.sp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(
                    BorderStroke(
                        width = if (isError && password.isEmpty()) 2.dp else 0.dp,
                        color = if (isError && password.isEmpty()) Color.Red else Color.Gray
                    ), shape = RoundedCornerShape(4.dp)
                ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color.White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            trailingIcon = {
                IconButton(onClick = { stateIcon.value = !stateIcon.value }) {
                    Icon(
                        painter = painterResource(
                            id = if (stateIcon.value) R.drawable.ic_baseline_visibility_off_24
                            else R.drawable.ic_baseline_visibility_24
                        ),
                        contentDescription = "off",
                        tint = Color.Gray,
                        modifier = Modifier.size(18.dp)
                    )
                }
            },
            visualTransformation = if (stateIcon.value) PasswordVisualTransformation() else VisualTransformation.None
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = textStateFlow,
                color = Color.Red,
                fontSize = 14.sp
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "Забыли Пароль?",
                    color = Color.White,
                    modifier = Modifier.clickable { }
                )
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 40.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            onClick = {
                if (password.length >= 9) {
                    textStateFlow = ""
                } else {
                    textStateFlow = "Не менее 9 символов"
                }
                isError = number.isEmpty() || password.isEmpty() || password.length < 9
                if (!isError) {
                    // Навигация
                }
            }
        ) {
            Text(
                text = "Button",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Не зарегистрированы?", color = Color.Gray)
        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
        Text(text = "Регистрация",
            color = Color.Blue,
            modifier = Modifier.clickable {  })
    }
}
