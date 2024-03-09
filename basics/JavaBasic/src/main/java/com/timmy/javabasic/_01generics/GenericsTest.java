package com.timmy.javabasic._01generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsTest {

    public static void main(String[] args) {
        double res = addInt(1, 2);
        double resF = addFloat(1.0f, 2.0f);

        double res2 = add(1, 2);

        List<String> list = new ArrayList<>();
        // list.add(1);
        list.add("234");

        for (Object o : list) {
            System.out.println(o.toString());
        }

        Genertor<Food> foodGenertor = new Genertor<>();
        Genertor<Fruit> fruitGenertor = new Genertor<>();
        Genertor<Apple> appleGenertor = new Genertor<>();
        Genertor<Orange> orangeGenertor = new Genertor<>();
//        print(fruitGenertor);
//        print(appleGenertor);
//        print(orangeGenertor);

        Genertor<? extends Fruit> genertor1 = new Genertor<>();
//        genertor1.setData(new Apple());
        Fruit data3 = genertor1.getData();

//        printE(foodGenertor);
        printE(fruitGenertor);
        printE(appleGenertor);
        printE(orangeGenertor);
        Orange data = orangeGenertor.getData();
        Apple data1 = appleGenertor.getData();

        printS(foodGenertor);
        printS(fruitGenertor);
//        printS(appleGenertor);
//        printS(orangeGenertor);

        Genertor<? super Fruit> genertor = new Genertor<>();
        Object data2 = genertor.getData();
//         genertor.setData(new Food()); 报错

        Fruit fruit = new Apple();
//        Genertor<Fruit> fruitGenertor1 = new Genertor<Apple>();
        Genertor<? extends Fruit> fruitGenertor1 = new Genertor<Apple>();

        Genertor<? super Apple> fruitGenertor2 = new Genertor<Fruit>();
    }

    private static void print(Genertor<Fruit> t) {
        System.out.println(t.getData());
    }

    private static void printE(Genertor<? extends Fruit> t) {
        System.out.println(t.getData());
    }

    private static void printS(Genertor<? super Fruit> t) {
        System.out.println(t.getData());
    }


    private static <T extends ArrayList & Comparable> T min(T a, T b) {
        if (a.compareTo(b) > 0) {
            return a;
        } else {
            return b;
        }
    }

    private static <T> T getElement(T... a) {
        return a[a.length / 2];
    }

    private static double addFloat(float v, float v1) {
        return v + v1;
    }

    private static double addInt(int n1, int n2) {
        return n1 + n2;
    }

    public static <T extends Number> double add(T t1, T t2) {
        return t1.doubleValue() + t2.doubleValue();
    }


}
