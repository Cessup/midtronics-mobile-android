package com.cessup.midtronics.platform.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cessup.midtronics.domain.model.Resume
import com.cessup.midtronics.domain.model.School
import com.cessup.midtronics.domain.model.User
import com.cessup.midtronics.domain.model.Work
import com.cessup.midtronics.domain.repositories.UserRepository
import com.cessup.midtronics.platform.ui.UiState
import com.cessup.midtronics.platform.utils.handleNetworkError
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _picture = MutableStateFlow("")
    val picture : StateFlow<String> = _picture

    init {
        loadUser()
    }

    private fun loadUser() {
        val idUser = 1;
        _picture.value = userRepository.getUserPicture()


        viewModelScope.launch {
                val (userResult, schoolResult, workResult) =
                    awaitAll(
                    async { userRepository.getUser(idUser).first() },
                    async { userRepository.getSchoolsListByIdUser(idUser).first() },
                    async { userRepository.getWorkListByIdUser(idUser).first() }
                )

                if(
                    userResult.isSuccess &&
                    schoolResult.isSuccess &&
                    workResult.isSuccess
                ) {
                    _uiState.value = UiState.Success(Resume(
                        userResult.getOrNull() as User,
                        schoolResult.getOrNull() as List<School>,
                        workResult.getOrNull() as List<Work>))
                } else {
                    val errorMessage =
                        handleNetworkError(
                            (userResult.exceptionOrNull()
                                ?: schoolResult.exceptionOrNull()
                                ?: workResult.exceptionOrNull()
                                ?: Exception("Unknown error")) as Exception
                        )
                    _uiState.value = UiState.NetworkError(errorMessage)
                }
        }
    }
}