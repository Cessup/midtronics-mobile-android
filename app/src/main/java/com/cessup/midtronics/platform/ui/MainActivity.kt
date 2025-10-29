package com.cessup.midtronics.platform.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cessup.midtronics.platform.navigation.AppNavHost
import com.cessup.midtronics.platform.ui.theme.MidtronicsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MidtronicsTheme {
                AppNavHost()
            }
        }
    }
}