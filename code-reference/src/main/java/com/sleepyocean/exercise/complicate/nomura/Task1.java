package com.sleepyocean.exercise.complicate.nomura;

public class Task1 {
    public static void main(String[] args) {
        System.out.println(new Task1().solution("azABabbza"));
    }

    // Dynamic Programming
    public int solution(String str) {
        if (str.length() < 2)
            return -1;
        int length = str.length();
        int min = length + 1;

        for (int currentIndex = 1; currentIndex < length; currentIndex++) {
            for (int i = 0; i < currentIndex; i++) {
                int j = i;
                while (j < currentIndex) {
                    if (isBalance(str.substring(j, currentIndex + 1))) {
                        min = Math.min(min, currentIndex - j + 1);
                    }
                    j++;
                }
            }
        }

        return min;
    }

    // Slider Window
    public int solution1(String str) {
        int l = 0;
        int r = 0;
        int min = -1;
        while (r < str.length()) {
            while (l < r && str.substring(l + 1, r + 1).indexOf(str.charAt(l)) >= 0) {
                l++;
            }
            String current = str.substring(l, r + 1);
            while (isBalance(current) && l < r) {
                min = min == -1 ? r - l + 1 : Math.min(min, r - l + 1);
                l++;
                System.out.println("balance str: " + current);
            }
            r++;
        }
        return min;
    }

    private boolean isBalance(String s) {
        if (s.length() < 2)
            return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(balanceChar(s.charAt(i))) < 0) {
                return false;
            }
        }
        return true;
    }

    private char balanceChar(char c) {
        if (c > 64 && c < 91) {
            return (char) (c + 32);
        } else if (c > 96 && c < 123) {
            return (char) (c - 32);
        } else {
            return (char) 0;
        }
    }
}
