package org.michaelbel.cutouts.sample05_Never

import android.app.Activity
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.plus
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.michaelbel.cutouts.ConstantBadge
import org.michaelbel.cutouts.CutoutDiagram
import org.michaelbel.cutouts.DescriptionCard
import org.michaelbel.cutouts.WhenToUseCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sample05Screen(onBack: () -> Unit) {
    BackHandler(onBack = onBack)

    val window = (LocalContext.current as Activity).window
    DisposableEffect(Unit) {
        val prev = window.attributes.layoutInDisplayCutoutMode
        window.attributes = window.attributes.apply {
            layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER
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
                title = { Text("Режим «Никогда»") },
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
            item { ConstantBadge("LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER") }
            item {
                DescriptionCard(
                    description = "Окно никогда не расширяется в зону выреза дисплея. Окно всегда отображается " +
                        "с отступами (letterbox или pillarbox), полностью избегая зоны выреза. " +
                        "Контент никогда не отрисовывается за вырезом."
                )
            }
            item {
                WhenToUseCard(
                    items = listOf(
                        "Интерфейс, который не может безопасно адаптироваться к вырезу",
                        "Полноэкранное воспроизведение видео — letterbox предпочтительнее перекрытия контента",
                        "Игры, где недопустим любой пиксель под вырезом",
                        "Унаследованные приложения, не рассчитанные на edge-to-edge разметку",
                        "Контент, который нельзя обрезать или перекрывать аппаратными элементами",
                    )
                )
            }
            item { CutoutDiagram(mode = "NEVER") }
        }
    }
}
