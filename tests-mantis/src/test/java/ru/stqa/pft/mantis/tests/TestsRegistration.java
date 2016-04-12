package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by oleg on 12.04.16.
 */
public class TestsRegistration extends TestBase {

    @Test
    public void testRegistration() {
        app.registration().start();
    }
}
