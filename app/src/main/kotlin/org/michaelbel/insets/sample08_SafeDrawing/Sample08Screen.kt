package org.michaelbel.insets.sample08_SafeDrawing

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.michaelbel.insets.BulletPoint
import org.michaelbel.insets.ConstantBadge
import org.michaelbel.insets.InfoCard
import org.michaelbel.insets.InfoRow

@Composable
fun Sample08Screen() {
    val density = LocalDensity.current
    val safeDrawingInsets = WindowInsets.safeDrawing
    val topDp = with(density) { safeDrawingInsets.getTop(density).toDp() }
    val bottomDp = with(density) { safeDrawingInsets.getBottom(density).toDp() }
    val leftDp = with(density) { safeDrawingInsets.getLeft(density, androidx.compose.ui.unit.LayoutDirection.Ltr).toDp() }
    val rightDp = with(density) { safeDrawingInsets.getRight(density, androidx.compose.ui.unit.LayoutDirection.Ltr).toDp() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Безопасные отступы рисования") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding + PaddingValues(
                top = 16.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item { ConstantBadge("Modifier.safeDrawingPadding()") }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "Что делает",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Применяет отступ, эквивалентный WindowInsets.safeDrawing — объединению всех " +
                                "системных отступов, которых следует избегать при отрисовке: строка состояния, " +
                                "панель навигации, вырез дисплея и IME (клавиатура).",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Modifier.safeDrawingPadding()",
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = FontFamily.Monospace,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = "// equivalent to:\nModifier.windowInsetsPadding(WindowInsets.safeDrawing)",
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = FontFamily.Monospace,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
            item {
                InfoCard(title = "Текущие отступы safeDrawing") {
                    InfoRow("сверху", "$topDp")
                    InfoRow("снизу", "$bottomDp")
                    InfoRow("слева", "$leftDp")
                    InfoRow("справа", "$rightDp")
                }
            }
            item { SafeDrawingVisual() }
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            "Включённые отступы",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                        )
                        BulletPoint("Строка состояния (верхняя системная панель)")
                        BulletPoint("Панель навигации (жестовая зона или кнопки)")
                        BulletPoint("Вырез дисплея (чёлка / отверстие / водопад)")
                        BulletPoint("IME / экранная клавиатура (когда видима)")
                    }
                }
            }
        }
    }
}

@Composable
private fun SafeDrawingVisual() {
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
                "Без и с safeDrawingPadding",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PhoneMockup(
                    modifier = Modifier.weight(1f),
                    label = "без",
                    hasPadding = false
                )
                PhoneMockup(
                    modifier = Modifier.weight(1f),
                    label = "с",
                    hasPadding = true
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                LegendChip(
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.errorContainer,
                    label = "Зона системного UI"
                )
                LegendChip(
                    modifier = Modifier.weight(1f),
                    color = MaterialTheme.colorScheme.primaryContainer,
                    label = "Безопасное содержимое"
                )
            }
        }
    }
}

@Composable
private fun PhoneMockup(modifier: Modifier = Modifier, label: String, hasPadding: Boolean) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(1.5.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.4f))
            )
            if (hasPadding) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "безопасно",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "небезопасно",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun LegendChip(modifier: Modifier = Modifier, color: androidx.compose.ui.graphics.Color, label: String) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .height(12.dp)
                .fillMaxWidth(0.15f)
                .clip(RoundedCornerShape(4.dp))
                .background(color)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
