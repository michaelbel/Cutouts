@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class
)

package org.michaelbel.insets.sample12_SafeDrawing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
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
fun Sample12Screen() {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val safeDrawing = WindowInsets.safeDrawing

    val safeDrawingTop = safeDrawing.getTop(density)
    val safeDrawingBottom = safeDrawing.getBottom(density)
    val safeDrawingLeft = safeDrawing.getLeft(density, layoutDirection)
    val safeDrawingRight = safeDrawing.getRight(density, layoutDirection)

    val hasSafeDrawing = safeDrawingTop > 0 || safeDrawingBottom > 0 || safeDrawingLeft > 0 || safeDrawingRight > 0

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Безопасная зона рисования") },
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
                    trailingContent = { Text(if (hasSafeDrawing) "ДА" else "НЕТ") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item { SectionLabel("Отступы") }
            item {
                val topDp = with(density) { safeDrawingTop.toDp() }
                ListItem(
                    headlineContent = { Text("Сверху") },
                    trailingContent = { Text("$safeDrawingTop px  ($topDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val bottomDp = with(density) { safeDrawingBottom.toDp() }
                ListItem(
                    headlineContent = { Text("Снизу") },
                    trailingContent = { Text("$safeDrawingBottom px  ($bottomDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val leftDp = with(density) { safeDrawingLeft.toDp() }
                ListItem(
                    headlineContent = { Text("Слева") },
                    trailingContent = { Text("$safeDrawingLeft px  ($leftDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val rightDp = with(density) { safeDrawingRight.toDp() }
                ListItem(
                    headlineContent = { Text("Справа") },
                    trailingContent = { Text("$safeDrawingRight px  ($rightDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
        }
    }
}
