package com.cessup.midtronics.platform.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cessup.midtronics.domain.repositories.UserRepository
import com.cessup.midtronics.platform.utils.handleNetworkError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userName = MutableStateFlow("Welcome ")
    val userName : StateFlow<String> = _userName

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            userRepository.getUser(1).collect { result ->
                result.onSuccess { message ->
                    _userName.value = "Welcome ${result.getOrNull()?.details?.name}"
                }.onFailure { exception ->
                    val errorMessage = handleNetworkError(exception as Exception)
                    _userName.value = errorMessage
                }
            }
        }
    }
}