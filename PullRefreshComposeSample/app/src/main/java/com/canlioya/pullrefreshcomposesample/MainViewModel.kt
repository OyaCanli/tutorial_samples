package com.canlioya.pullrefreshcomposesample

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canlioya.pullrefreshcomposesample.data.ColorItemDataSource
import com.canlioya.pullrefreshcomposesample.data.Result
import com.canlioya.pullrefreshcomposesample.pullrefresh.PullToRefreshLayoutState
import com.canlioya.pullrefreshcomposesample.pullrefresh.RefreshIndicatorState
import com.canlioya.pullrefreshcomposesample.utils.DateUtils
import com.canlioya.pullrefreshcomposesample.utils.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val colorItemDataSource: ColorItemDataSource,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    val pullToRefreshState = PullToRefreshLayoutState(
        onTimeUpdated = { timeElapsed ->
            convertElapsedTimeIntoText(timeElapsed)
        },
    )

    val scrollState = LazyListState()

    init {
        fetchData(UiState.LoadingType.INITIAL_LOAD)
    }

    private fun fetchData(loadType: UiState.LoadingType) {
        viewModelScope.launch {
            colorItemDataSource.getColorList().map { result ->
                when (result) {
                    Result.Loading -> {
                        if (loadType == UiState.LoadingType.INITIAL_LOAD) {
                            UiState.Loading
                        } else {
                            _uiState.value
                        }
                    }

                    is Result.Error -> UiState.Error
                    is Result.Success -> {
                        if (loadType == UiState.LoadingType.PULL_REFRESH) {
                            pullToRefreshState.updateRefreshState(RefreshIndicatorState.Default)
                            scrollState.scrollToItem(0)
                        }
                        UiState.Success(result.data)
                    }
                }
            }.collectLatest { result ->
                _uiState.value = result
            }
        }
    }

    fun refresh() {
        fetchData(UiState.LoadingType.PULL_REFRESH)
    }

    fun convertElapsedTimeIntoText(timeElapsed: Long): String {
        return DateUtils.getTimePassedInHourMinSec(resourceProvider, timeElapsed)
    }
}
