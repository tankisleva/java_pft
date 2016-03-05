package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by oleg on 28.02.16.
 */
public class GroupModificationTests extends TestBase {


    @Test
    public void testGroupModification()  {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().editSelectionGroups();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test4"));
        app.getGroupHelper().updateGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
