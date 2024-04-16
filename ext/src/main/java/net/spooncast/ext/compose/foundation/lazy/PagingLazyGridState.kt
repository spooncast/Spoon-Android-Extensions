package net.spooncast.ext.compose.foundation.lazy

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun rememberPagingLazyGridState(
    prefetchDistance: Int = 5,
    onScrollToBottom: () -> Unit
): LazyGridState {
    val listState = rememberLazyGridState()
    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisibleItemIdx = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: Int.MIN_VALUE
            val lastIndex = listState.layoutInfo.totalItemsCount - 1
            lastVisibleItemIdx >= lastIndex - prefetchDistance
        }
    }
    LaunchedEffect(key1 = shouldLoadMore) {
        if (shouldLoadMore) {
            onScrollToBottom()
        }
    }

    return listState
}