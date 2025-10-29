package com.cessup.midtronics.platform.ui.countries

import androidx.lifecycle.ViewModel
import com.cessup.midtronics.domain.repositories.CountriesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CountryDetailsViewModel(
    private val countriesRepository: CountriesRepository
) : ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    init {
        loadUser()
    }

    private fun loadUser() {
        // Simulate getting data from the repository
        //_userName.value = userRepository.getUserName()
    }
}