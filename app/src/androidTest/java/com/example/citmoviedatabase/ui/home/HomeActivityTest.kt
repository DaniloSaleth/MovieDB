package com.example.citmoviedatabase.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.citmoviedatabase.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest{
    @get:Rule
    val activityRule = ActivityTestRule(HomeActivity::class.java, false, false)

    @Test
    fun appLaunchesSuccessfully() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun onLaunchCheckNowPlayingIsDisplayed() {
        ActivityScenario.launch(HomeActivity::class.java)

        onView(withId(R.id.nowPaying))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenOkayButtonIsPressedAndAmountIsEmptyTipIsEmpty() {
        ActivityScenario.launch(HomeActivity::class.java)



        onView(withId(R.id.comingSoon))
            .perform(click())
    }
}
