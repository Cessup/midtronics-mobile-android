package com.cessup.midtronics.platform.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cessup.midtronics.domain.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userName = MutableStateFlow("Welcome:")
    val userName : StateFlow<String> = _userName

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            userRepository.getUserDetails(1).collect { user ->
                _userName.value = "Welcome ${user?.name}"
            }
        }
    }
}