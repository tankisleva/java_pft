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
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0)
        {app.group().create(new GroupData().withName("test").withHeader("test2").withFooter(null));}
    }



    @Test
    public void testGroupModification()  {
//        List<GroupData> before =  app.group().list();
//        Set<GroupData> before =  app.group().all();
        Groups before =  app.group().all();
//        int index = before.size()-1;
        GroupData modifyGroup = before.iterator().next();
        GroupData group = new GroupData().withName("tes8").withHeader("test12").withFooter("test45").withId(modifyGroup.getId());
        app.group().modify(group);
//        List<GroupData> after =  app.group().list();
//        Set<GroupData> after =  app.group().all();
        Groups after =  app.group().all();
//        Assert.assertEquals(after.size(),before.size());
        assertThat(after.size(),equalTo(before.size()));
//        before.remove(modifyGroup);
//        before.add(group);
//        Comparator<? super GroupData> byId = (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
//        before.sort(byId);
//        after.sort(byId);
//        Assert.assertEquals(before,after);
        assertThat(after,equalTo(before.withOut(modifyGroup).withAdded(group)));

//        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
    }


}
