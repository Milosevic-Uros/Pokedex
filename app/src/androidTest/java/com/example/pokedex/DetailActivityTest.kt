package com.example.pokedex


import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewInteraction
import androidx.test.filters.LargeTest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*

import com.example.pokedex.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun detailActivityTest() {
        val recyclerView = onView(
allOf(withId(R.id.myRecyclerView),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
3)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val textView = onView(
allOf(withId(R.id.pokemonName), withText("BULBASAUR"),
withParent(withParent(withId(android.R.id.content))),
isDisplayed()))
        textView.check(matches(withText("BULBASAUR")))

        val imageView = onView(
allOf(withId(R.id.pokemonImage),
withParent(withParent(withId(android.R.id.content))),
isDisplayed()))
        imageView.check(matches(isDisplayed()))

        val textView2 = onView(
allOf(withId(R.id.pokemonType1), withText("grass"),
withParent(withParent(withId(android.R.id.content))),
isDisplayed()))
        textView2.check(matches(withText("grass")))

        val textView3 = onView(
allOf(withId(R.id.pokemonType2), withText("poison"),
withParent(withParent(withId(android.R.id.content))),
isDisplayed()))
        textView3.check(matches(withText("poison")))

        val imageView2 = onView(
allOf(withId(R.id.weightImage),
withParent(allOf(withId(R.id.linearLayout2),
withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java)))),
isDisplayed()))
        imageView2.check(matches(isDisplayed()))

        val imageView3 = onView(
allOf(withId(R.id.heightImage),
withParent(allOf(withId(R.id.linearLayout),
withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java)))),
isDisplayed()))
        imageView3.check(matches(isDisplayed()))

        val textView4 = onView(
allOf(withId(R.id.pokemonWeight), withText("6.9 kg"),
withParent(allOf(withId(R.id.linearLayout2),
withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java)))),
isDisplayed()))
        textView4.check(matches(withText("6.9 kg")))

        val textView5 = onView(
allOf(withId(R.id.pokemonHeight), withText("70.0 cm"),
withParent(allOf(withId(R.id.linearLayout),
withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java)))),
isDisplayed()))
        textView5.check(matches(withText("70.0 cm")))

        val textView6 = onView(
allOf(withId(R.id.stats), withText("Stats:"),
withParent(withParent(withId(android.R.id.content))),
isDisplayed()))
        textView6.check(matches(withText("Stats:")))
        }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
    }
