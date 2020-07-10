package com.example.parstagram;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.internal.bytecode.SandboxClassLoader;
import org.robolectric.res.ResourceTable;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ParstagramUnitTests {

    /**
     * Testing Utils.getRelativeDate(Date)
     *  Return how long ago a Date was in string format i.e. "5 minutes ago"
     *  param: Date object
     *  returns: String
     */

    @Test
    public void getRelativeDate() {

        // 1 second ago
        Date oneSecondsAgo = new Date(System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(1));
        assertEquals("1 second ago", Utils.getRelativeTimeAgo(oneSecondsAgo));

        // 5 seconds ago
        Date fiveSecondsAgo = new Date(System.currentTimeMillis() - TimeUnit.SECONDS.toMillis(5));
        assertEquals("5 seconds ago", Utils.getRelativeTimeAgo(fiveSecondsAgo));

        // 1 minute ago
        Date oneMinuteAgo = new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(1));
        assertEquals("1 minute ago", Utils.getRelativeTimeAgo(oneMinuteAgo));

        // 5 minutes ago
        Date fiveMinutesAgo = new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(5));
        assertEquals("5 minutes ago", Utils.getRelativeTimeAgo(fiveMinutesAgo));

        // 1 hour ago
        Date oneHourAgo = new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1));
        assertEquals("1 hour ago", Utils.getRelativeTimeAgo(oneHourAgo));

        // 5 hours ago
        Date fiveHoursAgo = new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(5));
        assertEquals("5 hours ago", Utils.getRelativeTimeAgo(fiveHoursAgo));

        // Edge case: null
        assertEquals(Utils.getRelativeTimeAgo(null), "");

        // Edge case: Day in the future
        Date fiveHoursFuture = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(5));
        assertEquals("in 5 hours", Utils.getRelativeTimeAgo(fiveHoursFuture));

        // Edge case: Oldest unix time
        Date oldestDate = new Date(0);
        assertEquals("Dec 31, 1969", Utils.getRelativeTimeAgo(oldestDate));

    }
}