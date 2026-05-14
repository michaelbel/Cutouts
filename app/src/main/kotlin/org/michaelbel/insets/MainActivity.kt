@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class
)

package org.michaelbel.insets

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
import org.michaelbel.insets.sample01_CaptionBar.Sample01Screen
import org.michaelbel.insets.sample02_DisplayCutouts.Sample02Screen
import org.michaelbel.insets.sample04_Ime.Sample04Screen
import org.michaelbel.insets.sample05_MandatorySystemGestures.Sample05Screen
import org.michaelbel.insets.sample06_NavigationBars.Sample06Screen
import org.michaelbel.insets.sample07_StatusBars.Sample07Screen
import org.michaelbel.insets.sample08_SystemBars.Sample08Screen
import org.michaelbel.insets.sample09_SystemGestures.Sample09Screen
import org.michaelbel.insets.sample10_TappableElement.Sample10Screen
import org.michaelbel.insets.sample11_Waterfall.Sample11Screen
import org.michaelbel.insets.sample12_SafeDrawing.Sample12Screen
import org.michaelbel.insets.sample13_SafeGestures.Sample13Screen
import org.michaelbel.insets.sample14_SafeContent.Sample14Screen

class MainActivity: ComponentActivity() {

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
                            4 -> Sample04Screen()
                            5 -> Sample05Screen()
                            6 -> Sample06Screen()
                            7 -> Sample07Screen()
                            8 -> Sample08Screen()
                            9 -> Sample09Screen()
                            10 -> Sample10Screen()
                            11 -> Sample11Screen()
                            12 -> Sample12Screen()
                            13 -> Sample13Screen()
                            14 -> Sample14Screen()
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
                title = { Text(text = "Insets") },
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
                    shapes = ListItemDefaults.segmentedShapes(index = 0, count = 2),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("WindowInsets.captionBar") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(2) },
                    overlineContent = { Text("Sample 02") },
                    shapes = ListItemDefaults.segmentedShapes(index = 1, count = 2),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("WindowInsets.displayCutout") }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SegmentedListItem(
                    onClick = { onSampleClick(4) },
                    overlineContent = { Text("Sample 04") },
                    shapes = ListItemDefaults.segmentedShapes(index = 0, count = 7),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("WindowInsets.ime") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(5) },
                    overlineContent = { Text("Sample 05") },
                    shapes = ListItemDefaults.segmentedShapes(index = 1, count = 7),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("WindowInsets.mandatorySystemGestures") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(6) },
                    overlineContent = { Text("Sample 06") },
                    shapes = ListItemDefaults.segmentedShapes(index = 2, count = 7),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("WindowInsets.navigationBars") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(7) },
                    overlineContent = { Text("Sample 07") },
                    shapes = ListItemDefaults.segmentedShapes(index = 3, count = 7),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("WindowInsets.statusBars") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(8) },
                    overlineContent = { Text("Sample 08") },
                    shapes = ListItemDefaults.segmentedShapes(index = 4, count = 7),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("WindowInsets.systemBars") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(9) },
                    overlineContent = { Text("Sample 09") },
                    shapes = ListItemDefaults.segmentedShapes(index = 5, count = 7),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("WindowInsets.systemGestures") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(10) },
                    overlineContent = { Text("Sample 10") },
                    shapes = ListItemDefaults.segmentedShapes(index = 6, count = 7),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("WindowInsets.tappableElement") }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item {
                SegmentedListItem(
                    onClick = { onSampleClick(11) },
                    overlineContent = { Text("Sample 11") },
                    shapes = ListItemDefaults.segmentedShapes(index = 0, count = 4),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("WindowInsets.waterfall") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(12) },
                    overlineContent = { Text("Sample 12") },
                    shapes = ListItemDefaults.segmentedShapes(index = 1, count = 4),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("WindowInsets.safeDrawing") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(13) },
                    overlineContent = { Text("Sample 13") },
                    shapes = ListItemDefaults.segmentedShapes(index = 2, count = 4),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("WindowInsets.safeGestures") }
            }
            item {
                SegmentedListItem(
                    onClick = { onSampleClick(14) },
                    overlineContent = { Text("Sample 14") },
                    shapes = ListItemDefaults.segmentedShapes(index = 3, count = 4),
                    colors = ListItemDefaults.segmentedColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
                    )
                ) { Text("WindowInsets.safeContent") }
            }
        }
    }
}
