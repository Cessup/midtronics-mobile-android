package com.cessup.midtronics.platform.ui.profile

import androidx.lifecycle.ViewModel
import com.cessup.midtronics.domain.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HeadViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _picture = MutableStateFlow("")
    val picture : StateFlow<String> = _picture

    init {
        loadUser()
    }

    private fun loadUser() {
        _picture.value = userRepository.getUserPicture()
    }
}