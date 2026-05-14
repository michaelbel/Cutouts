@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class
)

package org.michaelbel.insets.sample04_Ime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
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
fun Sample04Screen() {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val ime = WindowInsets.ime

    val imeTop = ime.getTop(density)
    val imeBottom = ime.getBottom(density)
    val imeLeft = ime.getLeft(density, layoutDirection)
    val imeRight = ime.getRight(density, layoutDirection)

    val hasIme = imeTop > 0 || imeBottom > 0 || imeLeft > 0 || imeRight > 0

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Клавиатура") },
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
                    headlineContent = { Text("IME присутствует") },
                    trailingContent = { Text(if (hasIme) "ДА" else "НЕТ") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item { SectionLabel("Отступы") }
            item {
                val topDp = with(density) { imeTop.toDp() }
                ListItem(
                    headlineContent = { Text("Сверху") },
                    trailingContent = { Text("$imeTop px  ($topDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val bottomDp = with(density) { imeBottom.toDp() }
                ListItem(
                    headlineContent = { Text("Снизу") },
                    trailingContent = { Text("$imeBottom px  ($bottomDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val leftDp = with(density) { imeLeft.toDp() }
                ListItem(
                    headlineContent = { Text("Слева") },
                    trailingContent = { Text("$imeLeft px  ($leftDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val rightDp = with(density) { imeRight.toDp() }
                ListItem(
                    headlineContent = { Text("Справа") },
                    trailingContent = { Text("$imeRight px  ($rightDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
        }
    }
}
