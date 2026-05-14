@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class
)

package org.michaelbel.insets.sample09_SystemGestures

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import org.michaelbel.insets.SectionLabel

@Composable
fun Sample09Screen() {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val systemGestures = WindowInsets.systemGestures

    val systemGesturesTop = systemGestures.getTop(density)
    val systemGesturesBottom = systemGestures.getBottom(density)
    val systemGesturesLeft = systemGestures.getLeft(density, layoutDirection)
    val systemGesturesRight = systemGestures.getRight(density, layoutDirection)

    val hasSystemGestures = systemGesturesTop > 0 || systemGesturesBottom > 0 || systemGesturesLeft > 0 || systemGesturesRight > 0

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Системные жесты") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary
                ),
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap)
        ) {
            item { SectionLabel("Обнаружение") }
            item {
                ListItem(
                    headlineContent = { Text("Жесты присутствуют") },
                    trailingContent = { Text(if (hasSystemGestures) "ДА" else "НЕТ") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item { SectionLabel("Отступы") }
            item {
                val topDp = with(density) { systemGesturesTop.toDp() }
                ListItem(
                    headlineContent = { Text("Сверху") },
                    trailingContent = { Text("$systemGesturesTop px  ($topDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val bottomDp = with(density) { systemGesturesBottom.toDp() }
                ListItem(
                    headlineContent = { Text("Снизу") },
                    trailingContent = { Text("$systemGesturesBottom px  ($bottomDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val leftDp = with(density) { systemGesturesLeft.toDp() }
                ListItem(
                    headlineContent = { Text("Слева") },
                    trailingContent = { Text("$systemGesturesLeft px  ($leftDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val rightDp = with(density) { systemGesturesRight.toDp() }
                ListItem(
                    headlineContent = { Text("Справа") },
                    trailingContent = { Text("$systemGesturesRight px  ($rightDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
        }
    }
}
