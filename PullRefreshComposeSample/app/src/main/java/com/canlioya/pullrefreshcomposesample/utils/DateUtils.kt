package com.canlioya.pullrefreshcomposesample.utils

import com.canlioya.pullrefreshcomposesample.R
import java.util.concurrent.TimeUnit

object DateUtils {

    fun getTimePassedInHourMinSec(resourceProvider: ResourceProvider, timePassedMs: Long): String {
        return when {
            timePassedMs < TimeUnit.MINUTES.toMillis(1) -> {
                resourceProvider.getString(R.string.d_seconds_ago, TimeUnit.MILLISECONDS.toSeconds(timePassedMs))
            }

            timePassedMs < TimeUnit.HOURS.toMillis(1) -> {
                resourceProvider.getString(R.string.d_minutes_ago, TimeUnit.MILLISECONDS.toMinutes(timePassedMs))
            }

            timePassedMs < TimeUnit.HOURS.toMillis(4) -> {
                val hours = TimeUnit.MILLISECONDS.toHours(timePassedMs)
                val minutes =
                    TimeUnit.MILLISECONDS.toMinutes(timePassedMs - hours * TimeUnit.HOURS.toMillis(1))
                resourceProvider.getString(R.string.d_hours_ago, hours, minutes)
            }

            else -> ""
        }
    }

}