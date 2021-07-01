package com.example.nextbaseapp.ScreenShotTest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.facebook.testing.screenshot.Screenshot

import com.example.nextbaseapp.MyNextBaseApp
import com.example.nextbaseapp.di.DaggerAppComponent
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import com.karumi.shot.ScreenshotTest
import org.mockito.MockitoAnnotations

@LargeTest
@RunWith(AndroidJUnit4::class)
abstract class AcceptanceTest<T : Activity>(clazz: Class<T>) : ScreenshotTest {

    @Rule
    @JvmField
    val testRule: IntentsTestRule<T> = IntentsTestRule(clazz, true, false)

    fun startActivity(args: Bundle = Bundle()): T {
        val intent = Intent()
        intent.putExtras(args)
        return testRule.launchActivity(intent)
    }


}