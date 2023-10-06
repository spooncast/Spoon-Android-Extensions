package net.spooncast.ext.compose.foundation.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun rememberPagingPagerState(
    totalSize: Int,
    prefetchDistance: Int = 2,
    onScrollToTarget: () -> Unit
): PagerState {
    val pagerState = rememberPagerState()
    val shouldLoadMore by remember {
        derivedStateOf {
            val lastIndex = totalSize - 1
            pagerState.settledPage >= lastIndex - prefetchDistance
        }
    }
    LaunchedEffect(key1 = shouldLoadMore) {
        if (shouldLoadMore) {
            onScrollToTarget()
        }
    }

    return pagerState
}