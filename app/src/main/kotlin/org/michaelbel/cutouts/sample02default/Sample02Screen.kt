package org.michaelbel.cutouts.sample02default

import android.app.Activity
import android.view.WindowManager
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.michaelbel.cutouts.BulletPoint
import org.michaelbel.cutouts.ConstantBadge
import org.michaelbel.cutouts.CutoutDiagram
import org.michaelbel.cutouts.DescriptionCard
import org.michaelbel.cutouts.WhenToUseCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sample02Screen(onBack: () -> Unit) {
    BackHandler(onBack = onBack)

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
                title = { Text("Default Mode") },
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
            item { ConstantBadge("LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT") }
            item {
                DescriptionCard(
                    description = "The default behavior. The window may or may not extend into the display " +
                        "cutout region, depending on system and window configuration. In portrait with system " +
                        "bars visible, content is letterboxed to avoid the cutout."
                )
            }
            item {
                WhenToUseCard(
                    items = listOf(
                        "Portrait with status bar: content avoids the cutout",
                        "Landscape with status bar: content avoids both sides",
                        "Fullscreen (status bar hidden): content may extend into cutout",
                        "Best starting point for most apps — no explicit handling needed",
                    )
                )
            }
            item { CutoutDiagram(mode = "DEFAULT") }
        }
    }
}
