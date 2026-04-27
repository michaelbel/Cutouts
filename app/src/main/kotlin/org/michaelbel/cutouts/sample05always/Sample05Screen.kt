package org.michaelbel.cutouts.sample05always

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
                title = { Text("Always Mode") },
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
            item { ConstantBadge("LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS") }
            item {
                DescriptionCard(
                    description = "Added in API 30. The window always extends into the display cutout region, even " +
                        "when the window is in windowed mode (not fullscreen). Content may be rendered under the " +
                        "cutout area. You must use WindowInsets to offset interactive elements away from it."
                )
            }
            item {
                WhenToUseCard(
                    items = listOf(
                        "Available from API 30 (Android 11) and above — minSdk 34 makes this always available",
                        "Content extends into cutout area even in split-screen / windowed mode",
                        "Most aggressive mode — content always occupies the full screen including cutout zone",
                        "Always use WindowInsets.displayCutout to protect tappable UI elements",
                        "Useful for drawing backgrounds, gradients, and decorative elements edge-to-edge",
                    )
                )
            }
            item { CutoutDiagram(mode = "ALWAYS") }
        }
    }
}
