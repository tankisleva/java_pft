package ru.stqa.pft.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupCreationTests extends TestBase {


    @DataProvider
    public Iterator<Object[]> validGroups() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new GroupData().withName("test1").withHeader("header1").withFooter("footer1")});
        list.add(new Object[]{new GroupData().withName("test2").withHeader("header2").withFooter("footer2")});
        list.add(new Object[]{new GroupData().withName("test3").withHeader("header3").withFooter("footer3")});
        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> validGroupsFromFileCvs() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv"))))
        {
            String line = reader.readLine();

            while (line != null) {
                String[] split = line.split(";");
                list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
                line = reader.readLine();
            }

            return list.iterator();
        }
    }



    @DataProvider
    public Iterator<Object[]> validGroupsFromFileXml() throws IOException {
        try (BufferedReader reader =  new BufferedReader(new FileReader(new File("src/test/resources/groups.xml"))))
        {
        String xml = "";
        String line = reader.readLine();
        while (line != null){
            xml += line;
            line  = reader.readLine();
        }

        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
        return groups.stream().map((g)-> new Object[]{g}).collect(Collectors.toList()).iterator();
        }

    }



    @DataProvider
    public Iterator<Object[]> validGroupsFromFileJson() throws IOException {
        try (BufferedReader reader =  new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }

            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }

    }


    @Test(dataProvider = "validGroupsFromFileCvs",enabled = false)
    public void testGroupCreationCsv(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()+1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }

    @Test(dataProvider = "validGroupsFromFileJson")
    public void testGroupCreationJson(GroupData group) {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()+1));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }


    @Test(dataProvider = "validGroupsFromFileXml")
    public void testGroupCreationXml(GroupData group) {
        app.goTo().groupPage();
//        List<GroupData> before = app.group().list();
//        Set<GroupData> before = app.group().all();
        Groups before = app.group().all();
//        GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()+1));

//        List<GroupData> after = app.group().list();
//        Set<GroupData> after = app.group().all();
        Groups after = app.group().all();
//        assertEquals(after.size(),equalTo(before.size()+1));
//        group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
//        before.add(group);
//        assertThat(after.size(), equalTo(before.size()+1));

        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
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



    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test1").withHeader("test2'").withFooter("test3");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
//        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before));


    }

}