package org.michaelbel.cutouts.sample08waterfall

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.michaelbel.cutouts.BulletPoint
import org.michaelbel.cutouts.ConstantBadge
import org.michaelbel.cutouts.InfoCard
import org.michaelbel.cutouts.InfoRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sample08Screen(onBack: () -> Unit) {
    BackHandler(onBack = onBack)

    val density = LocalDensity.current
    val waterfallInsets = WindowInsets.waterfall
    val topDp = with(density) { waterfallInsets.getTop(density).toDp() }
    val bottomDp = with(density) { waterfallInsets.getBottom(density).toDp() }
    val leftDp = with(density) { waterfallInsets.getLeft(density, LayoutDirection.Ltr).toDp() }
    val rightDp = with(density) { waterfallInsets.getRight(density, LayoutDirection.Ltr).toDp() }
    val hasWaterfall = topDp > 0.dp || bottomDp > 0.dp || leftDp > 0.dp || rightDp > 0.dp

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Waterfall Insets") },
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
            item { ConstantBadge("WindowInsets.waterfall") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "What is a waterfall display?",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "A waterfall (or edge) display curves over the side edges of the device. " +
                                "The content visible in the curved portion may be distorted or unintentionally " +
                                "tapped. WindowInsets.waterfall reports how far the curved area extends from each edge.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
            item {
                InfoCard(
                    title = if (hasWaterfall) "Waterfall Insets (this device)" else "Waterfall Insets — no waterfall detected"
                ) {
                    InfoRow("top", "$topDp")
                    InfoRow("bottom", "$bottomDp")
                    InfoRow("left", "$leftDp")
                    InfoRow("right", "$rightDp")
                }
            }
            item { WaterfallDiagram(hasWaterfall = hasWaterfall) }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "How to handle",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                        )
                        BulletPoint("Avoid placing interactive elements in the waterfall zones")
                        BulletPoint("Use WindowInsets.waterfall.getLeft() / getRight() for precise avoidance")
                        BulletPoint("Waterfall insets are included in WindowInsets.displayCutout")
                        BulletPoint("Most prominent on devices like Samsung Galaxy S series with curved screens")
                        BulletPoint("Left/right are typically non-zero; top/bottom are usually zero")
                    }
                }
            }
        }
    }
}

@Composable
private fun WaterfallDiagram(hasWaterfall: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Waterfall zone (side curves)",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(160.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface),
            ) {
                val waterfallColor = if (hasWaterfall)
                    MaterialTheme.colorScheme.error.copy(alpha = 0.25f)
                else
                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)

                Row(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(16.dp)
                            .background(waterfallColor)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.surface),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "safe\ncontent",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(16.dp)
                            .background(waterfallColor)
                    )
                }
            }
            Text(
                text = if (hasWaterfall) "Red = curved waterfall zone (avoid placing tappable UI here)"
                else "No waterfall display detected on this device",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
