package com.example.telassessment;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.telassessment.ui.CardsListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewAssertTest {

    @Rule
    public ActivityTestRule<CardsListActivity> rule = new ActivityTestRule<>(CardsListActivity.class);

    @Before
    public void init() {
        rule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void ensureRecyclerViewIsPresent() {
        onView(withId(R.id.item_list))
                .check(matches((isDisplayed())));
    }

    @Test
    public void ensureRecyclerViewIsError() {
        onView(withId(R.id.tv_no_data))
                .check(matches(isDisplayed()));
    }
}
