package com.sleepyocean.exercise.complicate.ikm;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author gehoubao
 **/
public class Sum extends RecursiveTask<Long> {
    int low, hight;
    int[] array;

    public Sum(int[] array, int low, int hight) {
        this.low = low;
        this.hight = hight;
        this.array = array;
    }


    static long sumArray(int[] array) {
        return new ForkJoinPool().invoke(new Sum(array, 0, array.length));
    }

    @Override
    protected Long compute() {
        if (hight - low <= 4) {
            long sum = 0;
            for (int i = low; i < hight; ++i)
                sum += array[i];
            return sum;
        } else {
            int mid = low + (hight - low) / 2;
            Sum left = new Sum(array, low, mid);
            Sum right = new Sum(array, low, mid);
            left.fork();
            long rightAns = right.compute();
            long leftAns = left.join();
            return leftAns + rightAns;
        }
    }
}