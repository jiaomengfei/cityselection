package com.example.jiao.cityapplication;

import org.junit.Test;

public class ArrayMaxMin {

    //求这组数的最大数和最小数的绝对值是多少
    @Test
    public void getArrayMaxMinAbsolute() {
        int[] arr = new int[]{4, 6, 9, 52, 36, 97, -63, -55, -1, 64, -36};
        int len = arr.length;
        int max = 0;
        int min = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println(Math.abs(max-min));
    }
}
