package org.michaelbel.cutouts

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.children
import org.michaelbel.cutouts.databinding.ActivityMainBinding

class MainActivity: ComponentActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }
}

fun View.findViews(
    predicate: (View) -> Boolean
): List<View> {
    val root = this
    return buildList {
        if (predicate(root)) add(root)
        if (root is ViewGroup) {
            for (child in children) addAll(child.findViews(predicate))
        }
    }
}
