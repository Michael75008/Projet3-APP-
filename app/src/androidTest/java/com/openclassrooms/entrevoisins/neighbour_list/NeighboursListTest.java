package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Test class for list of neighbours
 */

@RunWith(JUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */

    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(Matchers.allOf(isDisplayed(), withId(R.id.list_neighbours)))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, we ensure that the item is no more shown
     */

    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We check the number of elements
        onView(Matchers.allOf(isDisplayed(), withId(R.id.list_neighbours))).check(withItemCount(ITEMS_COUNT));
        // We remove the element at position 1 when perform a click on a delete icon
        onView(Matchers.allOf(isDisplayed(), withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of elements is 12-1 so 11
        onView(Matchers.allOf(isDisplayed(), withId(R.id.list_neighbours))).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * We ensure that details page opens and details are correctly displayed
     */

    @Test
    public void myNeighbourList_detailsAction_shouldDisplayNeighbourDetails() {
        //We perform a click at position 0 which is Caroline
        onView(Matchers.allOf(isDisplayed(), withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //We check that Caroline details page has been opened and not another
        onView(withId(R.id.first_neighbour_name)).check(matches(withText("Caroline")));
    }

    /**
     * We ensure that neighbour set as favorite is correctly displayed in favorite list
     */

    @Test
    public void myNeighboursList_favoriteAction_setInFavoriteList() {
        //We perform a click at position 0 which is Caroline
        onView(Matchers.allOf(isDisplayed(), withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //We turn favorite Caroline
        onView(withId(R.id.floating_action_button)).perform(click());
        //We turn back to previous activity
        Espresso.pressBack();
        //We perform a click on Favorites tab to see favorite list
        onView(withContentDescription("favorites")).perform(click());
        //We check that Caroline has been correctly displayed on this list
        onView(Matchers.allOf(isDisplayed(), withId(R.id.item_list_name))).check(matches(withText("Caroline")));
    }
}

