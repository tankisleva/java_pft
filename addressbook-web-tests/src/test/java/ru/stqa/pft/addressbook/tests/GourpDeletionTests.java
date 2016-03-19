package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by oleg on 28.02.16.
 */
public class GourpDeletionTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0)
        {app.group().create(new GroupData().withName("test").withHeader("test2").withFooter(null));}
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(),before.size()-1);
        before.remove(index);
//        for (int i = 0; i < after.size(); i++)
//        {Assert.assertEquals(before.get(i),after.get(i));}
        Assert.assertEquals(before,after);
    }


}
