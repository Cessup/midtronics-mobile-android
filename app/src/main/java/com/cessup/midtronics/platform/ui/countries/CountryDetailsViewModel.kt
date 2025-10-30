package com.cessup.midtronics.platform.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cessup.midtronics.domain.repositories.CountriesRepository
import com.cessup.midtronics.platform.ui.UiState
import com.cessup.midtronics.platform.utils.handleNetworkError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryDetailsViewModel(
    private val countriesRepository: CountriesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun loadCountry(name: String) {
        viewModelScope.launch {
            val result = countriesRepository.getCountryDetails(name)
            result.collect { result ->
                result.onSuccess { message ->
                    _uiState.value = UiState.Success(result.getOrNull().orEmpty())
                }.onFailure { exception ->
                    val errorMessage = handleNetworkError(exception as Exception)
                    _uiState.value = UiState.NetworkError(errorMessage)
                }
            }
        }
    }
}