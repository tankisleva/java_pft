package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by oleg on 28.02.16.
 */
public class GourpDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereGroup())
        {app.getGroupHelper().createGroup(new GroupData("test", "test2", null));}
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectionGroups();
        app.getGroupHelper().returnToGroupPage();
    }
}
