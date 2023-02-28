package com.sleepyocean.exercise.complicate.nomura;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class T1 {
    public static void main(String[] args) {
        System.out.println(new T1().solution(new int[]{-1, 1, 2, 4}));
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        List<Integer> list = Arrays.stream(A).boxed().collect(Collectors.toList());
        Collections.sort(list);
        int last = -1;
        int m = 1;
        for (int i = 0; i < list.size() - 1; i++) {
            if (i > 0) last = list.get(i - 1);
            if (list.get(i) <= 0)
                continue;
            if (list.get(i) == m) {
                if (last < 0 || (last > 0 && last != m))
                    m++;
            }
            return m;
        }
        return m;
    }
}