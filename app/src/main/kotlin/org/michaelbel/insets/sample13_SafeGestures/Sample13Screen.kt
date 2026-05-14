@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class
)

package org.michaelbel.insets.sample13_SafeGestures

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeGestures
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
fun Sample13Screen() {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val safeGestures = WindowInsets.safeGestures

    val safeGesturesTop = safeGestures.getTop(density)
    val safeGesturesBottom = safeGestures.getBottom(density)
    val safeGesturesLeft = safeGestures.getLeft(density, layoutDirection)
    val safeGesturesRight = safeGestures.getRight(density, layoutDirection)

    val hasSafeGestures = safeGesturesTop > 0 || safeGesturesBottom > 0 || safeGesturesLeft > 0 || safeGesturesRight > 0

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Безопасная зона жестов") },
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
                    headlineContent = { Text("Зона присутствует") },
                    trailingContent = { Text(if (hasSafeGestures) "ДА" else "НЕТ") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item { SectionLabel("Отступы") }
            item {
                val topDp = with(density) { safeGesturesTop.toDp() }
                ListItem(
                    headlineContent = { Text("Сверху") },
                    trailingContent = { Text("$safeGesturesTop px  ($topDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val bottomDp = with(density) { safeGesturesBottom.toDp() }
                ListItem(
                    headlineContent = { Text("Снизу") },
                    trailingContent = { Text("$safeGesturesBottom px  ($bottomDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val leftDp = with(density) { safeGesturesLeft.toDp() }
                ListItem(
                    headlineContent = { Text("Слева") },
                    trailingContent = { Text("$safeGesturesLeft px  ($leftDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val rightDp = with(density) { safeGesturesRight.toDp() }
                ListItem(
                    headlineContent = { Text("Справа") },
                    trailingContent = { Text("$safeGesturesRight px  ($rightDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
        }
    }
}
