package org.michaelbel.cutouts.sample04_ShortEdges

import android.app.Activity
import android.view.WindowManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.michaelbel.cutouts.ConstantBadge
import org.michaelbel.cutouts.CutoutDiagram
import org.michaelbel.cutouts.DescriptionCard
import org.michaelbel.cutouts.WhenToUseCard

@Composable
fun Sample04Screen() {
    val window = (LocalContext.current as Activity).window
    DisposableEffect(Unit) {
        val prev = window.attributes.layoutInDisplayCutoutMode
        window.attributes = window.attributes.apply {
            layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
        onDispose {
            window.attributes = window.attributes.apply {
                layoutInDisplayCutoutMode = prev
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Режим коротких краёв") },
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
            item { ConstantBadge("LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES") }
            item {
                DescriptionCard(
                    description = "Окно может расширяться в зону выреза по коротким краям экрана (вверх и вниз " +
                        "в портретном режиме). Контент может отрисовываться под вырезом. " +
                        "Используйте WindowInsets.displayCutout, чтобы важные элементы интерфейса не попадали в эту зону."
                )
            }
            item {
                WhenToUseCard(
                    items = listOf(
                        "Портрет: контент может заходить за вырезы сверху и снизу",
                        "Альбомный режим: контент заходит в вырез короткого края (обычно сверху)",
                        "Применяется независимо от видимости системных панелей",
                        "Всегда используйте отступ WindowInsets.displayCutout для интерактивных элементов",
                        "Идеально для иммерсивного полноэкранного режима с чёлкой или отверстием в экране",
                    )
                )
            }
            item { CutoutDiagram(mode = "SHORT_EDGES") }
        }
    }
}
