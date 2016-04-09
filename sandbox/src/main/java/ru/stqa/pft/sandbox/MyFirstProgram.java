package ru.stqa.pft.sandbox;


public class MyFirstProgram {

    public static void main(String[] args) {


        MyPoint p1 = new MyPoint(2, 4);
        MyPoint p2 = new MyPoint(8, 6);


        System.out.printf("Расстояние между точкой (" + p1.x + ", " + p1.y + ") и точкой (" + p2.x + ", " + p2.y + ") в двумерной плоскости ранво " + p1.distance(p2));
    }



}








