package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by oleg on 28.02.16.
 */
public class GourpDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectionGroups();
        app.getGroupHelper().returnToGroupPage();
    }
}