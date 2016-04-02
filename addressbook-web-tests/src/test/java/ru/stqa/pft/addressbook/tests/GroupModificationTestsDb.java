package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by oleg on 02.04.16.
 */
public class GroupModificationTestsDb extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size()== 0)
        {   app.goTo().groupPage();
            app.group().create(new GroupData().withName("test").withHeader("test2").withFooter(null));
        } else {app.goTo().groupPage();}
    }



    @Test
    public void testGroupModificationDb()  {
        Groups before =  app.db().groups();
        GroupData modifyGroup = before.iterator().next();
        GroupData group = new GroupData().withName("tes8").withHeader("test12").withFooter("test45").withId(modifyGroup.getId());
        app.group().modify(group);
        assertThat(app.group().count(),equalTo(before.size()));
        Groups after =  app.db().groups();
        assertThat(after,equalTo(before.withOut(modifyGroup).withAdded(group)));
        verifyGroupListInUi();

    }

}
