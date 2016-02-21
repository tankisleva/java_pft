package ru.stqa.pft.sandbox;

import java.awt.*;

/**
 * Created by oleg on 21.02.16.
 */
public class MyPoint {

    public  double distance(Point p1, Point p2){

        return Math.sqrt ( Math.pow((p2.getX() - p1.getX()),2) + Math.pow((p2.getY() - p1.getY()),2));
    }
}
