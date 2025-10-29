package com.cessup.midtronics.platform.ui.home

import androidx.lifecycle.ViewModel
import com.cessup.midtronics.domain.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class HomeViewModel(
    private val userRepository: UserRepository
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