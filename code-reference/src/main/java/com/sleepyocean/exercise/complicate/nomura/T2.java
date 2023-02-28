package com.sleepyocean.exercise.complicate.nomura;

/**
 * @author gehoubao
 * @create 2021-09-05 15:42
 **/
public class T2 {

    public static void main(String[] args) {
        System.out.println(new T2().solution(new int[]{3, 1, 2}, new int[]{2, 3, 1})); // true
        System.out.println(new T2().solution(new int[]{1, 3, 2, 4}, new int[]{4, 1, 3, 2})); // true
        System.out.println(new T2().solution(new int[]{1, 2, 2, 3, 3}, new int[]{2, 3, 3, 4, 5})); // false
        System.out.println(new T2().solution(new int[]{1, 2, 1}, new int[]{2, 3, 3})); // false
        System.out.println(new T2().solution(new int[]{1, 2, 3, 4}, new int[]{2, 1, 4, 4})); // false
    }

    public boolean solution(int[] A, int[] B) {
        // write your code in Java SE 8
        int length = A.length;
        // boundary cond
        if (length < 2) return true;

        // store current vertices point to next vertices' info, index from 1
        int[] vertices = new int[length + 1];

        for (int i = 0; i < length; i++) {
            // if vertices point to next twice and more, it cannot be self-loops
            if (vertices[A[i]] != 0) return false;
            vertices[A[i]] = B[i];
        }

        // store current access vertices index, and start index from 1
        int currentIndex = 1;
        // count access times, for iteration
        int count = 0;

        while (count < length) {
            // without next vertices or point to self cannot not be self-loops
            if (vertices[currentIndex] == 0 || vertices[currentIndex] == currentIndex) return false;

            // store next vertices
            int tmp = vertices[currentIndex];
            // mark as visited
            vertices[currentIndex] = 0;
            currentIndex = tmp;
            count++;
        }
        // judge if return to start vertices index
        if (currentIndex != 1) return false;
        return true;
    }
}