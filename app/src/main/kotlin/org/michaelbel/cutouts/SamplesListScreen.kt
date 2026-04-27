package org.michaelbel.cutouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily

private data class Sample(
    val number: Int,
    val title: String,
    val description: String
)

private val samples = listOf(
    Sample(1, "Display Cutout Info", "safeInsets · boundingRects · waterfallInsets"),
    Sample(2, "Default Mode", "LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT"),
    Sample(3, "Short Edges Mode", "LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES"),
    Sample(4, "Never Mode", "LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER"),
    Sample(5, "Always Mode", "LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS"),
    Sample(6, "Safe Drawing Padding", "Modifier.safeDrawingPadding()"),
    Sample(7, "Display Cutout Insets", "WindowInsets.displayCutout"),
    Sample(8, "Waterfall Insets", "WindowInsets.waterfall"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SamplesListScreen(onSampleClick: (Int) -> Unit) {
    val scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(
        state = rememberTopAppBarState()
    )
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = "Cutouts") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary,
                ),
                scrollBehavior = scrollBehavior,
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
        ) {
            itemsIndexed(samples) { index, sample ->
                ListItem(
                    headlineContent = {
                        Text(text = sample.title)
                    },
                    supportingContent = {
                        Text(
                            text = sample.description,
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = FontFamily.Monospace,
                        )
                    },
                    leadingContent = {
                        Text(
                            text = sample.number.toString().padStart(2, '0'),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    },
                    trailingContent = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    },
                    modifier = Modifier.clickable { onSampleClick(sample.number) }
                )
                if (index < samples.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}
