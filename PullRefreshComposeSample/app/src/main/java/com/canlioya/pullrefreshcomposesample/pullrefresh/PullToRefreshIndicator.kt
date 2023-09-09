package com.canlioya.pullrefreshcomposesample.pullrefresh

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.canlioya.pullrefreshcomposesample.R
import kotlin.math.roundToInt

private const val maxHeight = 100

@Composable
fun PullToRefreshIndicator(
    indicatorState: RefreshIndicatorState,
    pullToRefreshProgress: Float,
    timeElapsed: String,
) {
    val heightModifier = when (indicatorState) {
        RefreshIndicatorState.PullingDown -> {
            Modifier.height(
                (pullToRefreshProgress * 100)
                    .roundToInt()
                    .coerceAtMost(maxHeight).dp,
            )
        }
        RefreshIndicatorState.ReachedThreshold -> Modifier.height(maxHeight.dp)
        RefreshIndicatorState.Refreshing -> Modifier.wrapContentHeight()
        RefreshIndicatorState.Default -> Modifier.height(0.dp)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .then(heightModifier)
            .padding(15.dp),
        contentAlignment = Alignment.BottomStart,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = stringResource(indicatorState.messageRes),
                style = MaterialTheme.typography.labelMedium,
                color = Color.Black,
            )
            if (indicatorState == RefreshIndicatorState.Refreshing) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    color = Color.Black,
                    trackColor = Color.Gray,
                    strokeWidth = 2.dp,
                )
            } else {
                Text(
                    text = stringResource(R.string.last_updated, timeElapsed),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Black,
                )
            }
        }
    }
}
