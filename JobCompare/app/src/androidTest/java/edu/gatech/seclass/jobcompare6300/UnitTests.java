package edu.gatech.seclass.jobcompare6300;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

public class UnitTests {
    @RunWith(AndroidJUnit4.class)
    public class SmallTestExamples {

        @Rule
        public ActivityTestRule<AddJobOfferActivity> tActivityRule = new ActivityTestRule<>(
                AddJobOfferActivity.class);

        @Test
        public void addJobOffer() {
            onView(withId(R.id.offerTitle)).perform(clearText(), replaceText("Software Engineer"));
            onView(withId(R.id.offerCompany)).perform(clearText(), replaceText("Google"));
            onView(withId(R.id.offerCity)).perform(clearText(), replaceText("Mountain View"));
            onView(withId(R.id.offerState)).perform(clearText(), replaceText("CA"));
            onView(withId(R.id.offerIndex)).perform(clearText(), replaceText("243"));
            onView(withId(R.id.offerSalary)).perform(clearText(), replaceText("130000"));
            onView(withId(R.id.offerBonus)).perform(clearText(), replaceText("20000"));
            onView(withId(R.id.offerRelocation)).perform(clearText(), replaceText("10000"));
            onView(withId(R.id.offerStock)).perform(clearText(), replaceText("80000"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.saveButtonID)).perform(click());
            // onView(withId(R.id.)).check(matches(withText()));
        }

        @Test
        public void invalidJobOffer() {
            onView(withId(R.id.offerTitle)).perform(clearText(), replaceText(""));
            onView(withId(R.id.offerCompany)).perform(clearText(), replaceText(""));
            onView(withId(R.id.offerCity)).perform(clearText(), replaceText(""));
            onView(withId(R.id.offerState)).perform(clearText(), replaceText(""));
            onView(withId(R.id.offerIndex)).perform(clearText(), replaceText(""));
            onView(withId(R.id.offerSalary)).perform(clearText(), replaceText(""));
            onView(withId(R.id.offerBonus)).perform(clearText(), replaceText(""));
            onView(withId(R.id.offerRelocation)).perform(clearText(), replaceText(""));
            onView(withId(R.id.offerStock)).perform(clearText(), replaceText(""));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.saveButtonID)).perform(click());
        }
    }
}
