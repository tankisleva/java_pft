package ru.stqa.pft.sandbox;

import java.awt.*;

public class MyFirstProgram {

    public static void main(String[] args) {

        Point p1 = new Point(2,4);
        Point p2 = new Point(8,6);
        MyPoint s = new MyPoint();

        System.out.printf("Расстояние между точкой ("+p1.getX()+", "+p1.getY()+") и точкой ("+p2.getX()+", "+p2.getY()+") в двумерной плоскости ранво " + s.distance(p1,p2));
    }




}
