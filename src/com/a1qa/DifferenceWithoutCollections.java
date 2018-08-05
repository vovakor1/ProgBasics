package com.a1qa;
public class DifferenceWithoutCollections {
static String result;
    public static void main(String[] args) {
        String[] namesOrigin = {"Alex", "Dima", "Kate", "Galina", "Ivan"};
        String[] namesDelete = {"Dima", "Ivan", "Kate"};
        Destinct (namesOrigin, namesDelete);
        String[] res = result.split(",");
        for (String s : res) {
            System.out.println(s);
        }
    }
    public static String Destinct (String[] x, String[] y) {
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < y.length; j++) {
                if (x[i] == y[j]) {
                    break;
                } else if (j == y.length - 1) {
                    result += x[i] + ",";
                }
            }
        }
        return (result);
    }
}
