package org.michaelbel.cutouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.michaelbel.cutouts.sample01cutoutinfo.Sample01Screen
import org.michaelbel.cutouts.sample02waterfall.Sample02Screen
import org.michaelbel.cutouts.sample03default.Sample03Screen
import org.michaelbel.cutouts.sample04shortedges.Sample04Screen
import org.michaelbel.cutouts.sample05never.Sample05Screen
import org.michaelbel.cutouts.sample06always.Sample06Screen
import org.michaelbel.cutouts.sample07safedrawing.Sample07Screen
import org.michaelbel.cutouts.sample08cutoutinsets.Sample08Screen
import org.michaelbel.cutouts.sample09waterfall.Sample09Screen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                var selectedSample by rememberSaveable { mutableIntStateOf(0) }
                when (selectedSample) {
                    0 -> SamplesListScreen(onSampleClick = { selectedSample = it })
                    1 -> Sample01Screen(onBack = { selectedSample = 0 })
                    2 -> Sample02Screen(onBack = { selectedSample = 0 })
                    3 -> Sample03Screen(onBack = { selectedSample = 0 })
                    4 -> Sample04Screen(onBack = { selectedSample = 0 })
                    5 -> Sample05Screen(onBack = { selectedSample = 0 })
                    6 -> Sample06Screen(onBack = { selectedSample = 0 })
                    7 -> Sample07Screen(onBack = { selectedSample = 0 })
                    8 -> Sample08Screen(onBack = { selectedSample = 0 })
                    9 -> Sample09Screen(onBack = { selectedSample = 0 })
                }
            }
        }
    }
}
