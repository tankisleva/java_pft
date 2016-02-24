package ru.stqa.pft.sandbox;

//import java.awt.*;

/**
 * Created by oleg on 21.02.16.
 */
public class MyPoint {

    public double x;
    public double y;

    MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(MyPoint p) {

        return Math.sqrt(Math.pow((p.x - this.x), 2) + Math.pow((p.y - this.y), 2));
    }


}
