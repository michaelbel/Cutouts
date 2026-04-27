package org.michaelbel.cutouts.sample03shortedges

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
fun Sample03Screen(onBack: () -> Unit) {
    BackHandler(onBack = onBack)

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
                title = { Text("Short Edges Mode") },
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
            item { ConstantBadge("LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES") }
            item {
                DescriptionCard(
                    description = "The window is allowed to extend into the display cutout region on the short " +
                        "edges of the screen (top and bottom in portrait). Content may be drawn under the cutout " +
                        "area. Use WindowInsets.displayCutout to keep important UI elements out of that zone."
                )
            }
            item {
                WhenToUseCard(
                    items = listOf(
                        "Portrait: content can extend behind top/bottom cutouts",
                        "Landscape: content extends into the short-edge (typically top) cutout",
                        "Applies regardless of system bar visibility",
                        "Always pair with WindowInsets.displayCutout padding on interactive elements",
                        "Ideal for immersive fullscreen experiences with a notch or punch-hole",
                    )
                )
            }
            item { CutoutDiagram(mode = "SHORT_EDGES") }
        }
    }
}
