package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oleg on 28.02.16.
 */
public class GourpDeletionTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0)
        {app.group().create(new GroupData().withName("test").withHeader("test2").withFooter(null));}
    }

    @Test
    public void testGroupDeletion() {
//        List<GroupData> before = app.group().list();
//        Set<GroupData> before = app.group().all();
        Groups before = app.group().all();
        GroupData deleteGroup = before.iterator().next();
//        int index = before.size()-1;
        app.group().delete(deleteGroup);
//        List<GroupData> after = app.group().list();
//        Set<GroupData> after = app.group().all();
        Groups after = app.group().all();
//        Assert.assertEquals(after.size(),before.size()-1);
        assertThat(after.size(),equalTo(before.size()-1));
//        before.remove(deleteGroup);
        assertThat(after,equalTo(before.withOut(deleteGroup)));
//        for (int i = 0; i < after.size(); i++)
//        {Assert.assertEquals(before.get(i),after.get(i));}
//        Assert.assertEquals(before,after);
    }


}
