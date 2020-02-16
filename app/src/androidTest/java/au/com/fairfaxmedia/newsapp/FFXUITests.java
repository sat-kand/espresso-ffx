package au.com.fairfaxmedia.newsapp;


import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import au.com.fairfaxmedia.newsapp.view.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class FFXUITests {

    @Rule public final ActivityRule<MainActivity> activityRule = new ActivityRule<>(MainActivity.class);

    //Verify the Title of Index screen
    @Test
    public  void verifyIndexScreenTitle() {

        onView(withText("NewsApp")).check(ViewAssertions.matches(isDisplayed()));
    }

    //Verify, stories has image, headline, theAbstract and byLine on index screen
    @Test
    public void verifyListStory() {

        onView(new RecyclerViewMatcher(R.id.recycler_view).atPosition(1)).check(matches(hasDescendant(withId(R.id.headline))));
        onView(new RecyclerViewMatcher(R.id.recycler_view).atPosition(1)).check(matches(hasDescendant(withId(R.id.theAbstract))));
        onView(new RecyclerViewMatcher(R.id.recycler_view).atPosition(1)).check(matches(hasDescendant(withId(R.id.byLine))));
        onView(new RecyclerViewMatcher(R.id.recycler_view).atPosition(1)).check(matches(hasDescendant(withId(R.id.thumbnail))));
    }

    //Verify, tapping a story should take user to full article
    @Test
    public void verifyOpenFullArticle() {

        onView(new RecyclerViewMatcher(R.id.recycler_view).atPosition(1)).perform(click());
        onView(withText("Home")).check(ViewAssertions.matches(isDisplayed()));
    }
}
