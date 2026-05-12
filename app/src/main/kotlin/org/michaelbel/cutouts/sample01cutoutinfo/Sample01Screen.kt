@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package org.michaelbel.cutouts.sample01cutoutinfo

import android.graphics.Rect
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.view.ViewCompat
import org.michaelbel.cutouts.SectionLabel

@Composable
fun Sample01Screen(onBack: () -> Unit) {
    BackHandler(onBack = onBack)

    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val cutoutTop = WindowInsets.displayCutout.getTop(density)
    val cutoutBottom = WindowInsets.displayCutout.getBottom(density)
    val cutoutLeft = WindowInsets.displayCutout.getLeft(density, layoutDirection)
    val cutoutRight = WindowInsets.displayCutout.getRight(density, layoutDirection)

    val hasDisplayCutout = cutoutTop > 0 || cutoutBottom > 0 || cutoutLeft > 0 || cutoutRight > 0


    val view = LocalView.current
    val windowInsets = remember(view) { ViewCompat.getRootWindowInsets(view) }
    val displayCutout = windowInsets?.displayCutout
    val safeInsets = displayCutout?.let {
        Rect(it.safeInsetLeft, it.safeInsetTop, it.safeInsetRight, it.safeInsetBottom)
    }
    val boundingRects = displayCutout?.boundingRects ?: emptyList()
    val waterfallInsets = displayCutout?.waterfallInsets

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text("Информация о вырезе дисплея") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
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
                    headlineContent = { Text("Вырез присутствует") },
                    trailingContent = { Text(if (hasDisplayCutout) "ДА" else "НЕТ") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }



            item {
                val position = displayCutout?.let {
                    buildList {
                        if (it.safeInsetTop > 0) add("СВЕРХУ")
                        if (it.safeInsetBottom > 0) add("СНИЗУ")
                        if (it.safeInsetLeft > 0) add("СЛЕВА")
                        if (it.safeInsetRight > 0) add("СПРАВА")
                    }.joinToString(", ")
                } ?: "—"
                ListItem(
                    headlineContent = { Text("Расположение выреза") },
                    trailingContent = { Text(position) },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                ListItem(
                    headlineContent = { Text("Ограничивающие прямоугольники") },
                    trailingContent = { Text("${boundingRects.size}") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }

            item { SectionLabel("Безопасные отступы") }
            item {
                val topDp = with(density) { (safeInsets?.top ?: 0).toDp() }
                ListItem(
                    headlineContent = { Text("Сверху") },
                    trailingContent = { Text("${safeInsets?.top ?: 0} px  ($topDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val bottomDp = with(density) { (safeInsets?.bottom ?: 0).toDp() }
                ListItem(
                    headlineContent = { Text("Снизу") },
                    trailingContent = { Text("${safeInsets?.bottom ?: 0} px  ($bottomDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val leftDp = with(density) { (safeInsets?.left ?: 0).toDp() }
                ListItem(
                    headlineContent = { Text("Слева") },
                    trailingContent = { Text("${safeInsets?.left ?: 0} px  ($leftDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val rightDp = with(density) { (safeInsets?.right ?: 0).toDp() }
                ListItem(
                    headlineContent = { Text("Справа") },
                    trailingContent = { Text("${safeInsets?.right ?: 0} px  ($rightDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }

            item { SectionLabel("Ограничивающие прямоугольники  (${boundingRects.size})") }
            if (boundingRects.isEmpty()) {
                item {
                    ListItem(
                        headlineContent = { Text("Нет") },
                        trailingContent = { Text("—") },
                        colors = ListItemDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                        )
                    )
                }
            } else {
                boundingRects.forEachIndexed { i, rect ->
                    item {
                        ListItem(
                            headlineContent = { Text("rect[$i]") },
                            trailingContent = { Text(rect.toInfoString(density)) },
                            colors = ListItemDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                            )
                        )
                    }
                }
            }

            item { SectionLabel("Отступы водопада") }
            item {
                val leftDp = with(density) { (waterfallInsets?.left ?: 0).toDp() }
                ListItem(
                    headlineContent = { Text("Слева") },
                    trailingContent = { Text("${waterfallInsets?.left ?: 0} px  ($leftDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val topDp = with(density) { (waterfallInsets?.top ?: 0).toDp() }
                ListItem(
                    headlineContent = { Text("Сверху") },
                    trailingContent = { Text("${waterfallInsets?.top ?: 0} px  ($topDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val rightDp = with(density) { (waterfallInsets?.right ?: 0).toDp() }
                ListItem(
                    headlineContent = { Text("Справа") },
                    trailingContent = { Text("${waterfallInsets?.right ?: 0} px  ($rightDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
            item {
                val bottomDp = with(density) { (waterfallInsets?.bottom ?: 0).toDp() }
                ListItem(
                    headlineContent = { Text("Снизу") },
                    trailingContent = { Text("${waterfallInsets?.bottom ?: 0} px  ($bottomDp)") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                )
            }
        }
    }
}

private fun Rect.toInfoString(density: Density): String {
    val leftDp = with(density) { left.toDp().value.toInt() }
    val topDp = with(density) { top.toDp().value.toInt() }
    val rightDp = with(density) { right.toDp().value.toInt() }
    val bottomDp = with(density) { bottom.toDp().value.toInt() }
    return "${leftDp}x${topDp} → ${rightDp}x${bottomDp} dp"
}