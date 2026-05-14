@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class
)

package org.michaelbel.insets.sample06_NavigationBars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
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
fun Sample06Screen() {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val navigationBars = WindowInsets.navigationBars

    val navigationBarsTop = navigationBars.getTop(density)
    val navigationBarsBottom = navigationBars.getBottom(density)
    val navigationBarsLeft = navigationBars.getLeft(density, layoutDirection)
    val navigationBarsRight = navigationBars.getRight(density, layoutDirection)

    val hasNavigationBars = navigationBarsTop > 0 || navigationBarsBottom > 0 || navigationBarsLeft > 0 || navigationBarsRight > 0

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Панель навигации") },
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
                    headlineContent = { Text("Панель присутствует") },
                    trailingContent = { Text(if (hasNavigationBars) "ДА" else "НЕТ") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item { SectionLabel("Отступы") }
            item {
                val topDp = with(density) { navigationBarsTop.toDp() }
                ListItem(
                    headlineContent = { Text("Сверху") },
                    trailingContent = { Text("$navigationBarsTop px  ($topDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val bottomDp = with(density) { navigationBarsBottom.toDp() }
                ListItem(
                    headlineContent = { Text("Снизу") },
                    trailingContent = { Text("$navigationBarsBottom px  ($bottomDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val leftDp = with(density) { navigationBarsLeft.toDp() }
                ListItem(
                    headlineContent = { Text("Слева") },
                    trailingContent = { Text("$navigationBarsLeft px  ($leftDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val rightDp = with(density) { navigationBarsRight.toDp() }
                ListItem(
                    headlineContent = { Text("Справа") },
                    trailingContent = { Text("$navigationBarsRight px  ($rightDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
        }
    }
}
