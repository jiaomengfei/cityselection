package com.example.jiao.cityapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    //有一组数，求这组数的最大数和最小数的绝对值是多少？
    @Test
    public void f4() {
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
        System.out.println(Math.abs(max - min));
    }
     //写一个字符串倒序的算法，请勿使用系统api
    @Test
    public void strRevers() {
        char[] str = "abcdefghijkl".toCharArray();
        int len = str.length;
        for (int i = 0; i < len / 2; i++) {
            char c = str[i];
            str[i] = str[len - 1 - i];
            str[len - 1 - i] = c;
        }
        System.out.println(str);
    }
//打印九九乘方表
    @Test
    public void f5()
    {
        for (int i = 1; i <= 9; i++)
        {
            for (int j = 1; j <= i; j++)
            {
                System.out.printf("%2dx%2d=%2d  ", j, i, i * j);
            }
            System.out.println("");
        }
    }
}