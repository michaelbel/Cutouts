@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)

package org.michaelbel.cutouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SegmentedListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.michaelbel.cutouts.sample01_CutoutInfo.Sample01Screen
import org.michaelbel.cutouts.sample02_Waterfall.Sample02Screen
import org.michaelbel.cutouts.sample03_Default.Sample03Screen
import org.michaelbel.cutouts.sample04_ShortEdges.Sample04Screen
import org.michaelbel.cutouts.sample05_Never.Sample05Screen
import org.michaelbel.cutouts.sample06_Always.Sample06Screen
import org.michaelbel.cutouts.sample07_SafeDrawing.Sample07Screen
import org.michaelbel.cutouts.sample08_CutoutInsets.Sample08Screen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                var selectedSample by rememberSaveable { mutableStateOf<Int?>(null) }
                when {
                    selectedSample == null -> SamplesListScreen { selectedSample = it }
                    else -> {
                        BackHandler { selectedSample = null }
                        when (selectedSample) {
                            1 -> Sample01Screen()
                            2 -> Sample02Screen()
                            3 -> Sample03Screen()
                            4 -> Sample04Screen()
                            5 -> Sample05Screen()
                            6 -> Sample06Screen()
                            7 -> Sample07Screen()
                            8 -> Sample08Screen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SamplesListScreen(onSampleClick: (Int) -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = "Cutouts") },
                colors = TopAppBarDefaults.topAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                scrollBehavior = scrollBehavior
            )
        },
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.only(WindowInsetsSides.Horizontal)
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                end = 16.dp,
                bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(ListItemDefaults.SegmentedGap)
        ) {
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(1) },
                    overlineContent = { Text("Sample 01") },
                    supportingContent = { Text("WindowInsets.displayCutout") },
                    shapes = ListItemDefaults.segmentedShapes(index = 0, count = 2),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("Информация о вырезе дисплея") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(2) },
                    overlineContent = { Text("Sample 02") },
                    supportingContent = { Text("WindowInsets.waterfall") },
                    shapes = ListItemDefaults.segmentedShapes(index = 1, count = 2),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("Отступы водопада") }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SegmentedListItem(
                    onClick = { onSampleClick(3) },
                    overlineContent = { Text("Sample 03") },
                    supportingContent = { Text("LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT") },
                    shapes = ListItemDefaults.segmentedShapes(index = 0, count = 4),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("Режим по умолчанию") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(4) },
                    overlineContent = { Text("Sample 04") },
                    supportingContent = { Text("LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES") },
                    shapes = ListItemDefaults.segmentedShapes(index = 1, count = 4),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("Режим коротких краёв") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(5) },
                    overlineContent = { Text("Sample 05") },
                    supportingContent = { Text("LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER") },
                    shapes = ListItemDefaults.segmentedShapes(index = 2, count = 4),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("Режим «Никогда»") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(6) },
                    overlineContent = { Text("Sample 06") },
                    supportingContent = { Text("LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS") },
                    shapes = ListItemDefaults.segmentedShapes(index = 3, count = 4),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("Режим «Всегда»") }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SegmentedListItem(
                    onClick = { onSampleClick(7) },
                    overlineContent = { Text("Sample 07") },
                    supportingContent = { Text("Modifier.safeDrawingPadding()") },
                    shapes = ListItemDefaults.segmentedShapes(index = 0, count = 2),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("Безопасные отступы рисования") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(8) },
                    overlineContent = { Text("Sample 08") },
                    supportingContent = { Text("WindowInsets.displayCutout") },
                    shapes = ListItemDefaults.segmentedShapes(index = 1, count = 2),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("Отступы выреза дисплея") }
            }
        }
    }
}
