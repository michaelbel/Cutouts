package org.michaelbel.cutouts

import android.content.Context
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Test

class CutoutsTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    @UiThreadTest
    fun findViewTest() {
        val root = LinearLayout(context)

        val button1 = Button(context).apply { isEnabled = true }
        root.addView(button1)

        val textView = TextView(context).apply { isVisible = true }
        root.addView(textView)

        val container = FrameLayout(context)
        val button2 = Button(context).apply { isEnabled = false }
        container.addView(button2)
        root.addView(container)

        val result = root.findViews { it is Button && it.isEnabled }

        assertEquals(1, result.size)
        assertSame(button1, result[0])
    }
}
