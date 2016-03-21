package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
//        List<GroupData> before = app.group().list();
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
        app.group().create(group);
//        List<GroupData> after = app.group().list();
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(),before.size()+1);
        group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before,after);

//        int max = 0;
//        for (GroupData g: after){
//            if (g.getId()>max){
//                max = g.getId();
//            }
//        }
//        Comparator<? super GroupData> byId = new Comparator<GroupData>() {
//            @Override
//            public int compare(GroupData o1, GroupData o2) {
//                return Integer.compare(o1.getId(),o2.getId());
//            }
//        };
//        int  max =  after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId();
//        group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
//        before.add(group);
//        Comparator<? super GroupData> byId = (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
//        before.sort(byId);
//        after.sort(byId);
//        Assert.assertEquals(before,after);
//        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

    }

}