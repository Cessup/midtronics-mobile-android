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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cessup.midtronics.R
import com.cessup.midtronics.platform.ui.UiState
import org.koin.androidx.compose.koinViewModel

/**
 * That is a screen to show a Countries List
 *
 * @author
 *     Cessup
 * @since 1.0
 */

/**
 * That is a screen to show a Countries List
 *
 * @param onActionCountriesList is the action to every item
 */
@Composable
fun CountriesScreen(onActionCountriesList: (String) -> Unit, onNavNetworkError: (String)->Unit) {
    val viewModel: CountriesViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        when (state) {
            is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            is UiState.NetworkError -> onNavNetworkError((state as UiState.NetworkError).message)
            is UiState.Success -> CountriesContent((state as UiState.Success).data as List<String>,onActionCountriesList)
            is UiState.Error -> Toast.makeText(context,(state as UiState.Error).message, Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
fun CountriesContent(countries:List<String>,onActionCountriesList: (String)->Unit){
    var searchQuery by remember { mutableStateOf("")}

    val filteredCountries = remember(searchQuery) {
        if (searchQuery.isBlank()) {
            countries
        } else {
            countries.filter { country ->
                // case-insensitive, matches any substring
                country.contains(searchQuery.trim(), ignoreCase = true)
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Countries",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Medium,
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search country") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clear_all),
                            contentDescription = "Clear"
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ){
            items(filteredCountries, key = { it }) { country->
                ItemCountry(country,onActionCountriesList)
            }
        }


    }
}

/**
 * That is a screen to a item that use in Countries List
 *
 * @param country is a text with the name to country
 * @param onActionCountriesList is an action after to click
 */
@Composable
fun ItemCountry(country: String, onActionCountriesList: (String)->Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
        ,
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = { onActionCountriesList(country) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(100.dp),
            contentAlignment = Alignment.Center // Centers both horizontally and vertically
        ) {
            Text(text = country,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}