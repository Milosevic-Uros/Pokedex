package com.example.pokedex


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val textView = onView(
allOf(withId(R.id.pokeTitle), withText("BULBASAUR"),
withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
isDisplayed()))
        textView.check(matches(withText("BULBASAUR")))

        val textView2 = onView(
allOf(withId(R.id.pokeTitle), withText("IVYSAUR"),
withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
isDisplayed()))
        textView2.check(matches(withText("IVYSAUR")))

        val textView3 = onView(
allOf(withId(R.id.pokeTitle), withText("VENUSAUR"),
withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
isDisplayed()))
        textView3.check(matches(withText("VENUSAUR")))

        val imageButton = onView(
allOf(withId(R.id.playButton),
withParent(withParent(withId(android.R.id.content))),
isDisplayed()))
        imageButton.check(matches(isDisplayed()))

        val imageButton2 = onView(
allOf(withId(R.id.playButton),
withParent(withParent(withId(android.R.id.content))),
isDisplayed()))
        imageButton2.check(matches(isDisplayed()))
        }
    }
