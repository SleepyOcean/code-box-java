package com.sleepyocean.exercise.complicate.ikm;

import java.util.Scanner;

/**
 * @author gehoubao
 **/
public class TestString {
    public static void main(String[] args) {
        StringBuilder str = new StringBuilder();
        for (String arg : args) {
            if (str.indexOf(arg) < 1) {
                str.append(arg + " ");
            }
        }
        System.out.println(str.toString());
        Scanner sc = new Scanner(str.toString());
        while (sc.hasNext()) {
            if (sc.hasNext()) {
                System.out.print(sc.nextInt() + " ");
            } else {
                sc.next();
            }
        }
    }
}