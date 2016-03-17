package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by oleg on 28.02.16.
 */
public class GourpDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereGroup())
        {app.getGroupHelper().createGroup(new GroupData("test", "test2", null));}
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().deleteSelectionGroups();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(),before.size()-1);
        before.remove(before.size()-1);
//        for (int i = 0; i < after.size(); i++)
//        {Assert.assertEquals(before.get(i),after.get(i));}
        Assert.assertEquals(before,after);
    }
}
