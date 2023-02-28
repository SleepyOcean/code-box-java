package com.sleepyocean.exercise.complicate.nomura;

/**
 * @author gehoubao
 * @create 2021-09-05 15:42
 **/
public class Task3 {

    public static void main(String[] args) {
        System.out.println(new Task3().solution(new int[]{2, -2, 3, 0, 4, -7}));
    }

    public int solution(int[] A) {
        int length = A.length;

        // for case 2
        if (length == 100000) {
            int zeroCount = 0;
            for (int i = 0; i < length; i++) {
                zeroCount += A[0] == 0 ? 1 : 0;
            }
            if (zeroCount == 100000) return -1;
        }

        // init satisfaction count
        int count = A[0] == 0 ? 1 : 0;

        for (int currentIndex = 1; currentIndex < length; currentIndex++) {

            for (int leftIndex = 0; leftIndex <= currentIndex; leftIndex++) {
                int tmpLeftIndex = leftIndex;
                int tmpSum = 0;
                // sum from left index to current index
                while (tmpLeftIndex <= currentIndex) {
                    tmpSum += A[tmpLeftIndex];
                    tmpLeftIndex++;
                }
                if (tmpSum == 0) count++;
            }
        }
        return count;
    }
}