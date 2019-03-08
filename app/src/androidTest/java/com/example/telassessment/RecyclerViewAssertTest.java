package com.example.telassessment;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.example.telassessment.ui.CardsListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
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
        if (getRVcount() > 0) {
            // check if displayed
            onView(withId(R.id.item_list))
                    .check(matches((isDisplayed())));

            // check if scroll present
            onView(withId(R.id.item_list))
                    .perform(RecyclerViewActions.scrollToPosition(0));

            // assert click
            try {
                // adding delay
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            onView(withId(R.id.item_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        }
    }


    @Test
    public void ensureRecyclerViewIsError() {
        // adding delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (getRVcount() == 0) {
            onView(withId(R.id.tv_no_data))
                    .check(matches(isDisplayed()));
        }
    }

    private int getRVcount() {
        RecyclerView recyclerView = rule.getActivity().findViewById(R.id.item_list);
        if (recyclerView != null && recyclerView.getAdapter() != null) {
            return recyclerView.getAdapter().getItemCount();
        }
        return 0;
    }
}

