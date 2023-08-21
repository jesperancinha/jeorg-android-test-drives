package com.joai.ping2;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](<a href="http://d.android.com/tools/testing">...</a>).
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void testContext(){
        var appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("org.joai.ping2", appContext.getPackageName());
    }
}