package com.example.gramm

import OSMMapView
import SelectedTariffInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gramm.Layout.Screens.ExpandableCard

import com.example.gramm.Layout.Screens.SplashScreen
import com.example.gramm.ui.theme.GrammTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GrammTheme {
//                    SplashScreen()
//                Prosses()
                OSMMapView()
//                KeyboardExample()
            }
        }
    }
}

