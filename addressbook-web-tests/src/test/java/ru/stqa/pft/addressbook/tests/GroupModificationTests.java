package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by oleg on 28.02.16.
 */
public class GroupModificationTests extends TestBase {


    @Test
    public void testGroupModification()  {
        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isThereGroup())
        {app.getGroupHelper().createGroup(new GroupData("test", "test2", null));}
        List<GroupData> before =  app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().editSelectionGroups();
        GroupData group = new GroupData("test1", "test2", "test4", before.get(before.size()-1).getId());
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().updateGroup();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after =  app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(),before.size());
        before.remove(before.size()-1);
        before.add(group);
        Comparator<? super GroupData> byId = (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
//        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    }
}
