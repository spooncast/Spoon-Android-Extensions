package net.spooncast.ext.compose.foundation.lazy

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun rememberPagingLazyListState(
    prefetchDistance: Int = 5,
    onScrollToBottom: () -> Unit
): LazyListState {
    val listState = rememberLazyListState()
    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisibleItemIdx = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: Int.MIN_VALUE
            val lastIndex = listState.layoutInfo.totalItemsCount - 1
            listState.canScrollBackward && lastVisibleItemIdx >= lastIndex - prefetchDistance
        }
    }
    LaunchedEffect(key1 = shouldLoadMore) {
        if (shouldLoadMore) {
            onScrollToBottom()
        }
    }

    return listState
}