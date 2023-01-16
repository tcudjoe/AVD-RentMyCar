package com.example.listapitest

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.core.AllOf
import org.hamcrest.core.Is
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.listapitest", appContext.packageName)
    }

    @get : Rule
    var mainActivity = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        //initial setup code
    }

    @Test
    //This test checks if the button to the car service overview works
    fun clickButtonService() {
        onView(withId(R.id.button_service)).perform(click())
    }

    @Test
    //This test checks if the button to the car rentals overview works
    fun clickButtonCustomer() {
        onView(withId(R.id.button_customer)).perform(click())
    }

    @Test
    //This test checks if the button to add a car works
    fun clickAddCarButton(){
        onView(withId(R.id.button_service)).perform(click())
        onView(withId(R.id.button_add_car)).perform(click())
    }

    @Test
    //This test checks of the right text is being typed in the correct inputs
    fun textValidationAddCar(){
        onView(withId(R.id.button_service)).perform(click());
        onView(withId(R.id.button_add_car)).perform(click());
        onView(withId(R.id.input_brand)).perform(typeText("BMW")).check(matches(withText("BMW")));
        onView(withId(R.id.input_model)).perform(typeText("330I")).check(matches(withText("330I")));
        onView(withId(R.id.input_year_of_build)).perform(typeText("2021")).check(matches(withText("2021")));
        onView(withId(R.id.input_kilometers)).perform(typeText("25892")).check(matches(withText("25892")));
        onView(withId(R.id.input_weight)).perform(typeText("1989")).check(matches(withText("1989")));
        onView(withId(R.id.input_category)).perform(typeText("ICE")).check(matches(withText("ICE")));
        onView(withId(R.id.input_location)).perform(typeText("somewhere")).check(matches(withText("somewhere")));
//        onView(withId(R.id.input_cost)).perform(typeText("30000.00")).check(matches(withText("30000.00")));
    }

    @Test
    //This test checks if the information will be added to the car service overview
    fun addCar(){
        onView(withId(R.id.button_service)).perform(click());
        onView(withId(R.id.button_add_car)).perform(click());
        onView(withId(R.id.input_brand)).perform(typeText("BMW")).check(matches(withText("BMW")));
        onView(withId(R.id.input_model)).perform(typeText("330I")).check(matches(withText("330I")));
        onView(withId(R.id.input_year_of_build)).perform(typeText("2021")).check(matches(withText("2021")));
        onView(withId(R.id.input_kilometers)).perform(typeText("25892")).check(matches(withText("25892")));
        onView(withId(R.id.input_weight)).perform(typeText("1989")).check(matches(withText("1989")));
        onView(withId(R.id.input_category)).perform(typeText("ICE")).check(matches(withText("ICE")));
        onView(withId(R.id.input_location)).perform(typeText("somewhere")).check(matches(withText("somewhere")));
//        onView(withId(R.id.input_cost)).perform(typeText("30000.00")).check(matches(withText("30000.00")));
        onView(withId(R.id.add_car)).perform(click());
//        onData(withId("item 4")).inAdapterView()
    }

    @Test
    //This test checks if the right text is being typed in the correct inputs
    fun textValidationFilter(){
        onView(withId(R.id.button_customer)).perform(click());
        onView(withId(R.id.max_kilometers_input)).perform(typeText("50000")).check(matches(withText("50000")));
        onView(withId(R.id.max_cost_input)).perform(typeText("20000")).check(matches(withText("20000")));
    }

    //    @Test
//    fun tes(){
//
//    }

    //    @Test
//    fun tes(){
//
//    }

    //    @Test
//    fun tes(){
//
//    }

    //    @Test
//    fun tes(){
//
//    }
    @After
    fun tearDown() {
        //clean up code
    }
}