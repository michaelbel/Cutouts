package org.michaelbel.insets.sample03_Default

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
import org.michaelbel.insets.BulletPoint
import org.michaelbel.insets.ConstantBadge
import org.michaelbel.insets.CutoutDiagram
import org.michaelbel.insets.DescriptionCard
import org.michaelbel.insets.WhenToUseCard

@Composable
fun Sample03Screen() {
    val window = (LocalContext.current as Activity).window
    DisposableEffect(Unit) {
        val prev = window.attributes.layoutInDisplayCutoutMode
        window.attributes = window.attributes.apply {
            layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
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
                title = { Text("Режим по умолчанию") },
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
            item { ConstantBadge("LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT") }
            item {
                DescriptionCard(
                    description = "Поведение по умолчанию. Окно может расширяться или не расширяться в зону " +
                        "выреза дисплея в зависимости от конфигурации системы и окна. В портретном режиме при " +
                        "видимых системных панелях контент отображается с отступом, чтобы не перекрывать вырез."
                )
            }
            item {
                WhenToUseCard(
                    items = listOf(
                        "Портрет со строкой состояния: контент не заходит в вырез",
                        "Альбомный режим со строкой состояния: контент избегает обеих сторон",
                        "Полноэкранный режим (строка скрыта): контент может зайти в вырез",
                        "Лучшая отправная точка для большинства приложений — явная обработка не нужна",
                    )
                )
            }
            item { CutoutDiagram(mode = "DEFAULT") }
        }
    }
}
