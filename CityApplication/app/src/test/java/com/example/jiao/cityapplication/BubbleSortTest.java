package com.example.jiao.cityapplication;

import android.util.Log;

import org.junit.Test;

import java.io.Console;

import static org.junit.Assert.assertEquals;

/*介绍：
        冒泡排序(Bubble Sort，台湾译为：泡沫排序或气泡排序)非常简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果他们的顺序错误就把他们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
        步骤：
        比较相邻的元素。如果第一个比第二个大，就交换他们两个。
        对每一对相邻元素作同样操作，从开始第一对到结尾的最后一对。在这一点，最后的元素会是最大的数。
        针对所有的元素重复以上的步骤，除了最后一个。
        持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。*/
public class BubbleSortTest {
    public  void bubble_sort(int[] unsorted) {
        for (int i = 0; i < unsorted.length; i++) {
            for (int j = i; j < unsorted.length; j++) {
                if (unsorted[i] > unsorted[j]) {
                    int temp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                }
            }
        }
    }

    @Test
    public void result() {
        int[] x = {6, 2, 4, 1, 5, 9};
        bubble_sort(x);
        for (int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }

    }
}