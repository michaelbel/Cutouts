package org.michaelbel.cutouts.sample01cutoutinfo

import android.graphics.Rect
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import org.michaelbel.cutouts.InfoCard
import org.michaelbel.cutouts.InfoRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sample01Screen(onBack: () -> Unit) {
    BackHandler(onBack = onBack)

    val view = LocalView.current
    val density = LocalDensity.current
    val windowInsets = remember(view) { ViewCompat.getRootWindowInsets(view) }
    val displayCutout = windowInsets?.displayCutout
    val safeInsets = displayCutout?.let {
        Rect(it.safeInsetLeft, it.safeInsetTop, it.safeInsetRight, it.safeInsetBottom)
    }
    val boundingRects = displayCutout?.boundingRects ?: emptyList()
    val waterfallInsets = displayCutout?.waterfallInsets

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Display Cutout Info") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding() + 16.dp,
                bottom = paddingValues.calculateBottomPadding() + 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                InfoCard(title = "Detection") {
                    InfoRow("cutout present", if (displayCutout != null) "true" else "false")
                    InfoRow("bounding rects", "${boundingRects.size}")
                }
            }
            item {
                InfoCard(title = "Safe Insets") {
                    val topDp = with(density) { (safeInsets?.top ?: 0).toDp() }
                    val bottomDp = with(density) { (safeInsets?.bottom ?: 0).toDp() }
                    val leftDp = with(density) { (safeInsets?.left ?: 0).toDp() }
                    val rightDp = with(density) { (safeInsets?.right ?: 0).toDp() }
                    InfoRow("top", "${safeInsets?.top ?: 0} px  ($topDp)")
                    InfoRow("bottom", "${safeInsets?.bottom ?: 0} px  ($bottomDp)")
                    InfoRow("left", "${safeInsets?.left ?: 0} px  ($leftDp)")
                    InfoRow("right", "${safeInsets?.right ?: 0} px  ($rightDp)")
                }
            }
            item {
                InfoCard(title = "Bounding Rects  (${boundingRects.size})") {
                    if (boundingRects.isEmpty()) {
                        InfoRow("none", "—")
                    } else {
                        boundingRects.forEachIndexed { i, rect ->
                            InfoRow("rect[$i]", rect.toInfoString(density))
                        }
                    }
                }
            }
            item {
                InfoCard(title = "Waterfall Insets") {
                    val leftDp = with(density) { (waterfallInsets?.left ?: 0).toDp() }
                    val topDp = with(density) { (waterfallInsets?.top ?: 0).toDp() }
                    val rightDp = with(density) { (waterfallInsets?.right ?: 0).toDp() }
                    val bottomDp = with(density) { (waterfallInsets?.bottom ?: 0).toDp() }
                    InfoRow("left", "${waterfallInsets?.left ?: 0} px  ($leftDp)")
                    InfoRow("top", "${waterfallInsets?.top ?: 0} px  ($topDp)")
                    InfoRow("right", "${waterfallInsets?.right ?: 0} px  ($rightDp)")
                    InfoRow("bottom", "${waterfallInsets?.bottom ?: 0} px  ($bottomDp)")
                }
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
