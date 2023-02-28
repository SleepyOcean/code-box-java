package com.sleepyocean.exercise.complicate.nomura;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class NomuraTest {

    public static void main(String[] args) {
        System.out.println(new NomuraTest().solution01(new int[]{-1, 1, 1, 3}));
    }

    public int solution01(int[] A) {
        // write your code in Java SE 8
        List<Integer> list = Arrays.stream(A).boxed().collect(Collectors.toList());
        Collections.sort(list);
        int last = -1;
        int m = 1;
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) last = list.get(i - 1);
            if (list.get(i) <= 0)
                continue;
            if (list.get(i) == m) {
                if (last <= 0) m++;
                else if (last > 0 && last != m) m++;
            } else {
                return m;
            }
        }
        return m;
    }

    public int solution(int[] array) {

        int length = array.length;
        //corner case
        if (length >= 10000) {
            return -1;
        }

        //定义一个长度也为length的result数组
        int[] result = new int[length];

        //初始值
        result[0] = array[0] == 0 ? 1 : 0;

        //动态递归
        for (int currentIndex = 1; currentIndex < length; currentIndex++) {
            result[currentIndex] = 0;
            for (int i = 0; i <= currentIndex; i++) {
                int j = i;
                int tempSum = 0;
                while (j <= currentIndex) {
                    tempSum += array[j];
                    j++;
                }
                if (tempSum == 0) {
                    result[currentIndex]++;
                }
            }
            result[currentIndex] += result[currentIndex - 1];
        }
        return result[length - 1];
    }
}
