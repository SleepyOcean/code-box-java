package com.sleepyocean.exercise.complicate.nomura;

public class Task2 {

    public static void main(String[] args) {
        System.out.println(new Task2().solution(new int[]{3, 1, 2}, new int[]{2, 3, 1})); // true
        System.out.println(new Task2().solution(new int[]{1, 3, 2, 4}, new int[]{4, 1, 3, 2})); // true
        System.out.println(new Task2().solution(new int[]{1, 2, 2, 3, 3}, new int[]{2, 3, 3, 4, 5})); // false
        System.out.println(new Task2().solution(new int[]{1, 2, 1}, new int[]{2, 3, 3})); // false
        System.out.println(new Task2().solution(new int[]{1, 2, 3, 4}, new int[]{2, 1, 4, 4})); // false
    }

    private boolean solution(int[] a, int[] b) {
        int length = a.length;
        if (length < 2)
            return true;

        int[] verticexs = new int[length + 1];

        for (int i = 0; i < length; i++) {
            if (verticexs[a[i]] != 0)
                return false;
            verticexs[a[i]] = b[i];
        }
        int index = 1;
        int count = 0;
        while (count < length) {
            // without next verticex or point to self should return false
            if (verticexs[index] == 0 || verticexs[index] == index) {
                return false;
            }
            int tmp = verticexs[index];
            // for next access should return false
            verticexs[index] = 0;
            index = tmp;
            count++;
        }

        // judge if reutrn to start verticex
        if (index != 1)
            return false;
        return true;
    }

}
