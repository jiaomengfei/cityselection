package com.example.jiao.cityapplication;

import org.junit.Test;

/**
 * Created by jiao3 on 2018/3/17.
 */

public class SelectionSort {

/*    介绍：
    选择排序(Selection sort)是一种简单直观的排序算法。它的工作原理如下。
    首先在未排序序列中找到最小元素，存放到排序序列的起始位置，
    然后再从剩余未排序元素中继续寻找最小元素，然后放到排序序列末尾。
    以此类推，直到所有元素均排序完毕。*/

    //选择排序
    @Test
    public void selectSort() {
        int[] array = {6, 2, 4, 1, 5, 9};
        int min;
        int tmp = 0;
        for (int i = 0; i < array.length; i++) {
            min = array[i];
            for (int j = i; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];//最小值
                    tmp = array[i];
                    array[i] = min;
                    array[j] = tmp;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
    }
}
