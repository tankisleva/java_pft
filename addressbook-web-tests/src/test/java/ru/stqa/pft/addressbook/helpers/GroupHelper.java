package ru.stqa.pft.addressbook.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by oleg on 28.02.16.
 */
public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }


    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void selectGroup(int i){
        wd.findElements(By.name("selected[]")).get(i).click();
//        click(By.name("selected[]"));
    }

    private void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();

    }

    public List <GroupData> list(){
        List <GroupData> groups = new ArrayList<>();
        List <WebElement> elements  = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withName(name).withHeader(null).withFooter(null).withId(id));
        }
        return groups;
    }


    private Groups groupCache = null;



    public Groups all(){
        if (groupCache != null){
           return groupCache =  new Groups(groupCache);
        }

        groupCache = new Groups();
        List <WebElement> elements  = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withName(name).withHeader(null).withFooter(null).withId(id));
        }
        return new Groups(groupCache);
    }


//    public Set<GroupData> all(){
//        Set <GroupData> groups = new HashSet<>();
//        List <WebElement> elements  = wd.findElements(By.cssSelector("span.group"));
//        for (WebElement element : elements){
//            String name = element.getText();
//            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//            groups.add(new GroupData().withName(name).withHeader(null).withFooter(null).withId(id));
//        }
//        return groups;
//    }



    public void deleteSelectionGroups(){
        click(By.name("delete"));
    }


    public void editSelectionGroups(){
        click(By.name("edit"));
    }

    public void updateGroup(){
        click(By.name("update"));
    }


    public boolean isThereGroup() {

        return isElementPresent(By.name("selected[]"));
    }

    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache = null;
       returnToGroupPage();
    }



    public void modify(GroupData group) {
        selectGroupById(group.getId());
       editSelectionGroups();
        fillGroupForm(group);
        updateGroup();
        groupCache = null;
        returnToGroupPage();
    }


    public void delete(int index) {
        selectGroup(index);
       deleteSelectionGroups();
       returnToGroupPage();
    }


    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelectionGroups();
        groupCache = null;
        returnToGroupPage();
    }


}
