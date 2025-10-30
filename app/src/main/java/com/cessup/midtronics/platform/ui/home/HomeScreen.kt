package com.cessup.midtronics.platform.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.cessup.midtronics.platform.ui.countries.CountriesScreen
import com.cessup.midtronics.platform.ui.profile.ProfileSection
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
fun HomeScreen(
    onNavProfile: (String) -> Unit,
    onActionCountriesList: (String) -> Unit,
    onNavNetworkError: (String) -> Unit) {
    val viewModel: HomeViewModel = koinViewModel()
    val name by viewModel.userName.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ProfileSection(title= name,onNavProfile)
        CountriesScreen(onActionCountriesList, onNavNetworkError )
    }
}
