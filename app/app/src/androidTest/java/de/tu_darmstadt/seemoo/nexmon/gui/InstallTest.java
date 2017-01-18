package de.tu_darmstadt.seemoo.nexmon.gui;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.tu_darmstadt.seemoo.nexmon.BuildConfig;
import de.tu_darmstadt.seemoo.nexmon.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InstallTest {

    @Rule
    public ActivityTestRule<MyActivity> mActivityTestRule = new ActivityTestRule<>(MyActivity.class);

    @Test
    public void installTest() throws InterruptedException {
        ViewInteraction button = onView(
                allOf(withId(R.id.btn_root), withText("Grant Root, Permission & Check Installation")));
        button.perform(scrollTo(), click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction imageButton = onView(
                allOf(withContentDescription("drawer open"),
                        withParent(allOf(withClassName(is("android.widget.Toolbar")),
                                withParent(withClassName(is("com.android.internal.widget.ActionBarContainer"))))),
                        isDisplayed()));
        try {
            Thread.sleep(500);

        } catch(Exception e) {e.printStackTrace();}
        imageButton.perform(click());

        try {
            Thread.sleep(500);

        } catch(Exception e) {e.printStackTrace();}
        //onData(hasEntry(equalTo("Data"), contains("Tools"))).perform(click());
        onData(anything()).atPosition(11).perform(click());
        //ViewInteraction textView = onView(
         //       allOf(withId(android.R.id.text1), withText("Tools"),
          //              childAtPosition(
           //                     allOf(withId(R.id.navList),
             //                           withParent(withId(R.id.drawerLayout))),
              //                  11),
               //         isDisplayed()));
        //textView.perform(click());


        ViewInteraction checkBox = onView(
                allOf(withId(R.id.chkRawproxy), withText("rawproxy: is needed to receive frames in this app.")));
        checkBox.perform(scrollTo(), click());

        ViewInteraction checkBox2 = onView(
                allOf(withId(R.id.chkRawproxyreverse), withText("rawproxyreverse: is needed to inject frames from this app.")));
        checkBox2.perform(scrollTo(), click());

        ViewInteraction checkBox3 = onView(
                allOf(withId(R.id.chkNexutil), withText("nexutil: sends ioctls to the WiFi firmware of Broadcom chips to change and check the status of monitor mode and promiscuous mode.")));
        checkBox3.perform(scrollTo(), click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.btnInstall), withText("Install binaries and libraries")));
        button2.perform(scrollTo(), click());

        try {
            Thread.sleep(5000);

        } catch(Exception e) {e.printStackTrace();}

        ViewInteraction imageButton2 = onView(
                allOf(withContentDescription("drawer open"),
                        withParent(allOf(withClassName(is("android.widget.Toolbar")),
                                withParent(withClassName(is("com.android.internal.widget.ActionBarContainer"))))),
                        isDisplayed()));
        try {
            Thread.sleep(500);

        } catch(Exception e) {e.printStackTrace();}
        imageButton2.perform(click());

        try {
            Thread.sleep(500);

        } catch(Exception e) {e.printStackTrace();}



        onData(anything()).inAdapterView(withId(R.id.navList)).atPosition(10).perform(click());

        try {
            Thread.sleep(500);

        } catch(Exception e) {e.printStackTrace();}

        ViewInteraction button3 = onView(
                allOf(withId(R.id.btnSearchFirmware), withText("Search Firmware")));
        button3.perform(scrollTo(), click());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.btnInstallNexmonFirmware), withText("Install Nexmon Firmware"),
                        withParent(withId(R.id.linDisclaimerDependent))));
        button4.perform(scrollTo(), click());

        ViewInteraction imageButton3 = onView(
                allOf(withContentDescription("drawer open"),
                        withParent(allOf(withClassName(is("android.widget.Toolbar")),
                                withParent(withClassName(is("com.android.internal.widget.ActionBarContainer"))))),
                        isDisplayed()));
        imageButton3.perform(click());

        try {
            Thread.sleep(500);

        } catch(Exception e) {e.printStackTrace();}

        onData(anything()).inAdapterView(withId(R.id.navList)).atPosition(0).perform(click());

        try {
            Thread.sleep(500);
        } catch(Exception e) {e.printStackTrace();}
        ViewInteraction textView4 = onView(
                allOf(withId(R.id.tv_nexmon_info),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                3),
                        isDisplayed()));
        textView4.check(matches(withText("App Version:\n\n" + BuildConfig.VERSION_NAME + "\n\nTool installation status:\n\nnexutil (" + BuildConfig.VERSION_NAME + ")\nrawproxy (" + BuildConfig.VERSION_NAME + ")\nrawproxyreverse (" + BuildConfig.VERSION_NAME + ")\n\nFirmware installation status:\n\nNexmon Firmware (" + BuildConfig.VERSION_NAME + ")\n\n")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
