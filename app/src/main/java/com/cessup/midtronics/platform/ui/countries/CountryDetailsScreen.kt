package com.cessup.midtronics.platform.ui.countries

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cessup.midtronics.R
import com.cessup.midtronics.domain.model.Country
import com.cessup.midtronics.platform.ui.UiState
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
fun DetailsCountryScreen(nameCountry: String, onNavNetworkError: (String)->Unit) {

    val viewModel: CountryDetailsViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    viewModel.loadCountry(nameCountry)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        when (state) {
            is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            is UiState.NetworkError -> onNavNetworkError((state as UiState.NetworkError).message)
            is UiState.Success -> DetailsCountryContent((state as UiState.Success).data as List<Country>)
            is UiState.Error -> Toast.makeText(context,(state as UiState.Error).message, Toast.LENGTH_LONG).show()
        }
    }
}

/**
 * That is a screen to a item that use in Countries List
 *
 * @param countries is a text with the name to country
 */
@Composable
fun DetailsCountryContent(countries: List<Country>){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ){
            items(countries) { country ->
                DetailsCountryItem(country)
            }
        }
    }
}

/**
 * That is a screen to a item that use in Countries List
 *
 * @param country is a text with the name to country
 */
@Composable
fun DetailsCountryItem(country: Country){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "${country.name.common}",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Capital: ${country.capital}"
        )

        Text(
            text = "Population: ${country.population}"
        )

        Text(
            text = "Region: ${country.region}"
        )
        Text(
            text = "Subregion: ${country.subregion}"
        )

        Spacer(modifier = Modifier.height(50.dp))

        AsyncImage(
            model = country.flags.png,
            contentDescription = country.name.common,
            placeholder = painterResource(R.drawable.ic_image_24),
            error = painterResource(R.drawable.ic_hide_image_24),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}