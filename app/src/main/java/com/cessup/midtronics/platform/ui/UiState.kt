package com.cessup.midtronics.platform.ui

import com.cessup.midtronics.domain.model.Country

sealed class UiState {
    object Loading : UiState()
    data class NetworkError(val message: String) : UiState()
    data class Success(val data: Any) : UiState()
    data class Error(val message: String) : UiState()
}