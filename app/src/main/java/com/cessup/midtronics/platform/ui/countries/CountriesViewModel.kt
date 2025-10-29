package com.cessup.midtronics.platform.ui.countries

import androidx.lifecycle.ViewModel
import com.cessup.midtronics.domain.repositories.CountriesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CountriesViewModel(
    private val countriesRepository: CountriesRepository
) : ViewModel() {

    private val _countriesList = MutableStateFlow(listOf("Australia", "Argentina","Bolivia","Colombia","Mexico","Panama","Puerto Rico"))
    val countries = _countriesList.asStateFlow()

    init {
        loadUser()
    }

    private fun loadUser() {
        // Simulate getting data from the repository
        //_userName.value = userRepository.getUserName()
    }
}