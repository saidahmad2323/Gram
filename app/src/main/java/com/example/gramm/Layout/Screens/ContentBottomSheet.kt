package com.example.gramm.Layout.Screens


import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramm.BottomBars
import com.example.gramm.CardDetailInfo
import com.example.gramm.R
import com.example.gramm.Tariff
import com.example.gramm.ui.theme.Grays
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun ContentBottomSheet(
    bottomSheetState: BottomSheetState,
    onclick: (Int) -> Unit
) {
    val bottomBarVisible = remember { mutableStateOf(true) }

    // Обработчик изменения состояния BottomSheet
    LaunchedEffect(bottomSheetState.isExpanded) {
        bottomBarVisible.value = !bottomSheetState.isExpanded
    }
    val isExpanded = bottomSheetState.isExpanded
    val state = remember { mutableStateOf(false) }
    val stateButton by remember { mutableStateOf(false) }
    val selectedButton = remember { mutableStateOf(true) }
    val selectedTariff =
        remember { mutableStateOf(Tariff("Эконом", R.drawable.ekonom, "12")) }
    val selectedTariffs =
        remember { mutableStateOf(Tariff("Курьер", R.drawable.ic_baseline_add_24, "12")) }
    Scaffold(
        modifier = Modifier.fillMaxHeight(0.93f),
        backgroundColor = if (bottomSheetState.isExpanded) Color.LightGray else Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = 0.dp,
                backgroundColor = Color.White,
                modifier = Modifier.padding(bottom = if (bottomSheetState.isExpanded) 5.dp else 0.dp)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.1f)
                            .padding(top = 7.dp, bottom = 2.dp)
                            .align(alignment = Alignment.CenterHorizontally)
                            .height(4.dp)
                            .background(
                                color = Color.LightGray,
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                    AdressCard(
                        color = Color.Cyan,
                        title = "Бозори Панчшанбе",
                        icon = R.drawable.ic_outline_circle_24
                    ) {
                        Card(
                            modifier = Modifier
                                .width(80.dp)
                                .height(39.dp),
                            elevation = 0.dp,
                            backgroundColor = Color.LightGray,
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Box(
                                modifier = Modifier.clickable { },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Подъезд",
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .wrapContentHeight(align = Alignment.CenterVertically)
                                )
                            }

                        }
                    }
                    AdressCard(
                        bottom = if (bottomSheetState.isExpanded) 0 else 10,
                        height = if (bottomSheetState.isExpanded) 0 else 1,
                        title = "Бозори Панчшнабе",
                        icon = R.drawable.ic_sharp_check_box_outline_blank_24
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "add", Modifier.size(40.dp), tint = Color.Gray
                        )
                    }
                }
            }
            if (!bottomSheetState.isExpanded) {
                Row(
                    Modifier.padding(bottom = 5.dp, start = 22.dp)
                ) {
                    ButtonType(title = "Такси", isSelected = selectedButton.value) {
                        if (!selectedButton.value) {
                            selectedButton.value = !selectedButton.value
                        }
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                    ButtonType(title = "Доставка", isSelected = !selectedButton.value) {
                        if (selectedButton.value) {
                            selectedButton.value = !selectedButton.value
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ExpandableCard(
                    state = isExpanded,
                    title = {
                        LazyRow(
                            modifier = Modifier.padding(horizontal = 22.dp)
                        ) {
                            val tariffs = listOf(
                                Tariff("Эконом", R.drawable.ekonom, "12"),
                                Tariff("Комфорт", R.drawable.komfort, "15"),
                                Tariff("Бизнес", R.drawable.biznes1, "20"),
                                Tariff("Караван", R.drawable.karavan, "25"),
                                Tariff("Минивэн", R.drawable.miniven, "30")
                            )
                            items(tariffs) { tariff ->
                                TariffCard(
                                    tariff = tariff,
                                    isSelected = selectedTariff.value == tariff,
                                    onSelected = {
                                        selectedTariff.value = tariff
                                    }
                                )
                            }
                        }
                    }, description = { SelectedTariffInfo(selectedTariff.value) })
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    shape = RoundedCornerShape(22.dp),
                    elevation = 0.dp,
                    backgroundColor = Color.White
                ) {
                    val cardDetailsList = listOf(
                        CardDetailInfo("Коментрий водителю", R.drawable.ic_backs) {
                            onclick(0)
                        },
                        CardDetailInfo("Заказ другому человеку", R.drawable.ic_backs) {
                            onclick(1)
                        },
                        CardDetailInfo("Запланировать поездку", R.drawable.ic_backs) {
                            onclick(2)
                        },
                    )
                    Column {
                        cardDetailsList.forEachIndexed { index, cardDetail ->
                            if (index > 0) {
                                Divider(
                                    modifier = Modifier
                                        .fillMaxWidth(0.88f)
                                        .align(
                                            alignment = Alignment.CenterHorizontally
                                        )
                                )
                            }
                            CardDeteils(
                                title = cardDetail.title,
                                icon = cardDetail.icon,
                                clickable = cardDetail.onClick
                            )
                        }
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 5.dp),
                    shape = RoundedCornerShape(22.dp),
                    elevation = 0.dp,
                    backgroundColor = Color.White
                ) {
                    Column {
                        CardDeteils(
                            title = "Добавить надбавки",
                            icon = R.drawable.ic_backs
                        ) {

                        }
                    }
                }
            }

        }
    }
}



@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun BottomSheetsss(
    titles: String,
    bottomSheetState: BottomSheetState,
) {
    var title by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.36f)
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

    }
}
