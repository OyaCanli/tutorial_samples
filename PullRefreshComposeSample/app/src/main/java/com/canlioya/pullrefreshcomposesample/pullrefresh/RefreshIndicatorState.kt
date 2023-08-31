package com.canlioya.pullrefreshcomposesample.pullrefresh

import androidx.annotation.StringRes
import com.canlioya.pullrefreshcomposesample.R

enum class RefreshIndicatorState(@StringRes val messageRes: Int) {
    Default(R.string.pull_to_refresh_complete_label),
    PullingDown(R.string.pull_to_refresh_pull_label),
    ReachedThreshold(R.string.pull_to_refresh_release_label),
    Refreshing(R.string.pull_to_refresh_refreshing_label)
}