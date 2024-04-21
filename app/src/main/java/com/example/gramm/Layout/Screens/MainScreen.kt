import android.annotation.SuppressLint
import android.os.Handler
import android.preference.PreferenceManager
import android.view.MotionEvent
import android.widget.Space
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.material.BottomSheetValue
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.MagnifierStyle.Companion.Default
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.gramm.BottomBars
import com.example.gramm.Layout.Screens.*
import com.example.gramm.R
import com.example.gramm.Tariff
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapListener
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import kotlin.random.Random


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@SuppressLint("ClickableViewAccessibility")
@Composable
fun OSMMapView() {
    val bottomSheetScaffoldStates = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val keyboardController = LocalSoftwareKeyboardController.current

    // Когда клавиатура открывается, убедимся, что BottomSheet поднимается выше
    val selectedTabIndex = remember { mutableStateOf(0) }
    val state = remember{ mutableStateOf(false)}
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldStates,
        sheetPeekHeight = 0.dp,
        sheetBackgroundColor = Color.White,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        drawerGesturesEnabled = true,
        sheetGesturesEnabled = true,
        sheetContent = {
            ContentByIndex(index = selectedTabIndex.value, bottomSheetScaffoldStates = bottomSheetScaffoldStates,
         )
        },
        modifier = Modifier.padding()
    ) {
        val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
        )
        val coroutine = rememberCoroutineScope()
        val context = LocalContext.current
        val centerLocation =
            remember { mutableStateOf(GeoPoint(40.2833, 69.6167)) } // Центральное местоположение
        val mapView = remember { mutableStateOf<MapView?>(null) }

        // Настройка конфигурации OSM
        Configuration.getInstance()
            .load(context, PreferenceManager.getDefaultSharedPreferences(context))

        Scaffold(
            bottomBar = {
                BottomBars(bottomSheetScaffoldState.bottomSheetState) {
                    coroutine.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isExpanded){
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                        else bottomSheetScaffoldState.bottomSheetState.expand()
                    }
                }
            },
            backgroundColor = Color.Gray
        ) { innerPadding ->
            BottomSheetScaffold(
                scaffoldState = bottomSheetScaffoldState,
                sheetPeekHeight = 300.dp,
                sheetBackgroundColor = Color.White,
                sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                drawerGesturesEnabled = false,
                sheetGesturesEnabled = true,
                sheetContent = {
                    ContentBottomSheet(
                        bottomSheetState = bottomSheetScaffoldState.bottomSheetState,
                        onclick = { index ->
                            selectedTabIndex.value = index
                            coroutine.launch {
                                bottomSheetScaffoldStates.bottomSheetState.expand()
                                state.value = true
                            }
                        }
                    )
                },
                modifier = Modifier.padding(innerPadding)
            ) {
                AndroidView(
                    factory = { ctx ->
                        MapView(ctx).apply {
                            mapView.value = this // Сохраняем ссылку на MapView
                            setTileSource(TileSourceFactory.MAPNIK)
                            setMinZoomLevel(4.0)
                            setMaxZoomLevel(20.0)
                            setBuiltInZoomControls(true)
                            setMultiTouchControls(true)
                            controller.setZoom(16.0)
                            controller.setCenter(centerLocation.value)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

