package com.example.redditek

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.content.Context
import android.content.SharedPreferences
import android.os.SystemClock
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import com.example.redditek.AuthenticationActivity
import org.junit.*
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.runners.MethodSorters

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ExampleInstrumentedTest {
    private var token: String? = null

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val MyPreference = MyPreference(context)
        token = MyPreference.access_token
        launchActivity<AuthenticationActivity>()
    }

    @After
    fun cleanUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val MyPreference = MyPreference(context)
        MyPreference.clearAll()
    }

    @Test
    fun test01_Login() {
        SystemClock.sleep(5000)
        onView(withId(R.id.signIn)).perform(click())
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val selector = UiSelector()

        try {
            UiScrollable(UiSelector().scrollable(true)).scrollForward(2)
            val chromeAccept = device.findObject(selector.textMatches("Autoriser"))
            SystemClock.sleep(2000)
            chromeAccept.waitForExists(10000)
            if (chromeAccept.exists()) {
                chromeAccept.click()
            }
        } catch (e: Exception) {
            Log.d("Error", e.localizedMessage)
        }

        SystemClock.sleep(9000)
        onView(withId(R.id.navigation_dashboard)).perform(click()).check(matches(isDisplayed()))
        SystemClock.sleep(2000)
        onView(withId(R.id.navigation_settings)).perform(click()).check(matches(isDisplayed()))
        SystemClock.sleep(2000)
        onView(withId(R.id.switch1)).perform(click()).check(matches(isDisplayed()))
        SystemClock.sleep(500)
        onView(withId(R.id.switch2)).perform(click()).check(matches(isDisplayed()))
        SystemClock.sleep(500)
        onView(withId(R.id.switch3)).perform(click()).check(matches(isDisplayed()))
        SystemClock.sleep(500)
        onView(withId(R.id.switch4)).perform(click()).check(matches(isDisplayed()))
        SystemClock.sleep(500)
        onView(withId(R.id.switch5)).perform(click()).check(matches(isDisplayed()))
        SystemClock.sleep(500)
        onView(withId(R.id.switch6)).perform(click()).check(matches(isDisplayed()))
        SystemClock.sleep(2000)
        onView(withId(R.id.navigation_dashboard)).perform(click()).check(matches(isDisplayed()))
        SystemClock.sleep(2000)
        onView(withId(R.id.navigation_home)).perform(click()).check(matches(isDisplayed()))
        SystemClock.sleep(2000)
        onView(withId(R.id.hot)).perform(click()).check(matches(isDisplayed()))
        SystemClock.sleep(2000)
        onView(withId(R.id.newest)).perform(click()).check(matches(isDisplayed()))
        SystemClock.sleep(2000)
        onView(withId(R.id.best)).perform(click()).check(matches(isDisplayed()))
        launchActivity<AuthenticationActivity>()
    }

    fun  Bottom_Bar() {
        SystemClock.sleep(5000)
        onView(withId(R.id.navigation_settings)).perform(click()).check(matches(isDisplayed()))
    }


    fun  Home() {
        onView(withId(R.id.navigation_home)).perform(click())
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val result = device.findObject(UiSelector().text("HOT"))

        result.waitForExists(10000)
        if (result.exists())
            Log.d("Test", "Home OK")
    }

    fun  Profile() {
        onView(withId(R.id.navigation_dashboard)).perform(click())
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        val result = device.findObject(UiSelector().text("Description"))

        result.waitForExists(10000)
        if (result.exists())
            Log.d("Test", "Profile OK")
    }



}