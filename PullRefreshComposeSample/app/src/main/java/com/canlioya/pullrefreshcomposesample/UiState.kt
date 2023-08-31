package com.canlioya.pullrefreshcomposesample

import androidx.compose.ui.graphics.Color

sealed interface UiState {
    object Loading : UiState
    object Error : UiState
    data class Success(val data: List<Color>) : UiState

    enum class LoadingType {
        INITIAL_LOAD, PULL_REFRESH,
    }
}
