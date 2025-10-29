package com.cessup.midtronics.platform.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cessup.midtronics.platform.ui.countries.CountriesScreen
import org.koin.androidx.compose.koinViewModel

/**
 * That is the Home for everything
 *
 * This class contain the start flow
 *
 * @author
 *     Cessup
 * @since 1.0
 */
@Composable
fun HomeScreen(onActionCountriesList: (String) -> Unit,
               onNavNetworkError: (String) -> Unit) {

    val viewModel: HomeViewModel = koinViewModel()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        CountriesScreen(onActionCountriesList, onNavNetworkError )
    }
}
