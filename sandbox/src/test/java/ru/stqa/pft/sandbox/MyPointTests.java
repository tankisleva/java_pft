package ru.stqa.pft.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by oleg on 25.02.16.
 */
public class MyPointTests {

    @Test
    public void testDistanceCase1() {
        MyPoint p1 = new MyPoint(2, 4);
        MyPoint p2 = new MyPoint(8, 6);
        Assert.assertEquals(p1.distance(p2), 6.324555320336759);
    }


    @Test
    public void testDistanceCase2() {
        MyPoint p1 = new MyPoint(1.0, 3.0);
        MyPoint p2 = new MyPoint(11.5, 6.2);
        Assert.assertEquals(p1.distance(p2), 13.97679370308106);
    }
}
