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
public class GroupDeletionTestsDb extends TestBase {


    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size()== 0)
        {   app.goTo().groupPage();
            app.group().create(new GroupData().withName("test").withHeader("test2").withFooter(null));}

        else {app.goTo().groupPage();}
    }

    @Test
    public void testGroupDeletionDb() {
        Groups before = app.db().groups();
        GroupData deleteGroup = before.iterator().next();
        app.group().delete(deleteGroup);
        assertThat(app.group().count(),equalTo(before.size()-1));
        Groups after =  app.db().groups();
        assertThat(after,equalTo(before.withOut(deleteGroup)));
    }
}
