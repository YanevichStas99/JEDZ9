package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        MyMap<Integer> myMap=new MyTreeMap();
        myMap.put(5,10);
        myMap.put(4,11);
        myMap.put(6,66);
        myMap.put(2,78);
        System.out.println(myMap.containsKey(6));
        myMap.put(3,68);
        myMap.put(8,88);
        myMap.put(7,77);
        System.out.println(myMap.put(9,97));
        myMap.put(10,100);
        System.out.println(myMap.containsValue(100));
        System.out.println(myMap.put(9,97));
        System.out.println(myMap.get(5));
        System.out.println(myMap.remove(7));
        System.out.println(myMap.containsKey(7));
        System.out.println(myMap.size());
        System.out.println(myMap);
        myMap.clear();
        System.out.println(myMap);


    }
}
