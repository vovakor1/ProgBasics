package com.a1qa;

import java.util.ArrayList;
import java.util.List;

public class  DifferenceWithCollections{
    static List<String> result = new ArrayList<>();
    public static void main(String[] args) {
        ArrayList[] namesOrigin = {"Alex", "Dima", "Kate", "Galina", "Ivan"};
        ArrayList[] namesDelete = {"Dima", "Ivan", "Kate"};
        Subtract(namesOrigin, namesDelete);

        for (String res : result) {
            System.out.println(res);
        }
    }
    public static List<String> Subtract (String[] x, String[] y){
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < y.length; j++) {
                if (x[i] == y[j]) {
                    break;
                }
                else if (j == y.length - 1) {
                    result.add(x[i]);
                }
            }
        }
        return result;
    }
}
