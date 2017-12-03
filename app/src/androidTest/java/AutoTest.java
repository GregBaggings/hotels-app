import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.git.thesis.hotelapp.R;
import io.git.thesis.hotelapp.activities.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Geri on 2017.11.12..
 */

@RunWith(AndroidJUnit4.class)
public class AutoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void checkDestinationFieldVisibilityTest(){
        onView(withId(R.id.destinationTxtView)).check(matches(isDisplayed()));
    }
}
