package org.michaelbel.cutouts.sample06_Always

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
fun Sample06Screen() {
    val window = (LocalContext.current as Activity).window
    DisposableEffect(Unit) {
        val prev = window.attributes.layoutInDisplayCutoutMode
        window.attributes = window.attributes.apply {
            layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
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
                title = { Text("Режим «Всегда»") },
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
            item { ConstantBadge("LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS") }
            item {
                DescriptionCard(
                    description = "Добавлено в API 30. Окно всегда расширяется в зону выреза дисплея, даже " +
                        "в оконном режиме (не полноэкранном). Контент может отрисовываться под вырезом. " +
                        "Необходимо использовать WindowInsets для отступа интерактивных элементов от выреза."
                )
            }
            item {
                WhenToUseCard(
                    items = listOf(
                        "Доступно с API 30 (Android 11) и выше — minSdk 34 гарантирует поддержку",
                        "Контент заходит в зону выреза даже в режиме разделённого экрана / оконном режиме",
                        "Самый агрессивный режим — контент всегда занимает весь экран включая зону выреза",
                        "Всегда используйте WindowInsets.displayCutout для защиты нажимаемых элементов",
                        "Полезно для отрисовки фонов, градиентов и декоративных элементов от края до края",
                    )
                )
            }
            item { CutoutDiagram(mode = "ALWAYS") }
        }
    }
}
