package com.example.nextbaseapp.view.fragment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nextbaseapp.ChartView.MainActivity
import com.example.nextbaseapp.ScreenShotTest.AcceptanceTest
import com.facebook.testing.screenshot.Screenshot
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ScreenshotNameTest : AcceptanceTest<MainActivity>(MainActivity::class.java) {

    @Test
    fun screenshotHasACustomName() {
        val activity = startActivity()
        compareScreenshot(activity, name = "aCustomName")
    }


}