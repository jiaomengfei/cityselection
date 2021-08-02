package com.example.jiao.cityapplication;

import org.junit.Test;

import java.util.Arrays;

public class MergeOrderedArray {

    @Test
    public void getResult() {
        int[] a = {1, 2, 2, 3, 4, 5};
        int[] b = {5, 7, 9, 13};
        System.out.println(Arrays.toString(mergeArr(a,b)));
    }

    /**
     * 思路： 1.新建一个数组大小为firArr和secArr之和的数组mergeArr
     * 2.如果fistArr的第一个元素大于等于secArr的最后一个元素,则直接对mergeArr进行元素赋值
     * 3.如果secArr的第一个元素大于secArr的最后一个元素,则直接对mergeArr进行元素赋值
     * 4.若不满足上面两种情况，则需要进行数组拆分
     *     1)设置数组firArr和数组secArr的索引index，初始值都为0
     *     2)循环mergeArr数组
     *     3)如果当前firArr的索引小于firArr数组大小且secArr的索引小于secArr数组的大小，判断firArr、secArr两数组对应位置的元素大小，将值小的数组的那个值赋值给mergeArr，并向后移动一位此数组的index
     *     4)如果当前位置小于firArr或者secArr两数组的大小，进行相应的复制(走到这个循环中，只能表示说，有一个数组已经索引完毕了)
     *
     * @param firArr
     *            第一个数组
     * @param secArr
     *            第二个数组
     * @return 合并之后的数组
     */

    private static int[] mergeArr(int[] firArr, int[] secArr) {
        int firlen = firArr.length;
        int seclen = secArr.length;
        int[] mergeArr = new int[firlen + seclen];

        if (firArr[0] >= secArr[seclen - 1]) {
            for (int i = 0; i < mergeArr.length; i++) {
                if (i < seclen) {
                    mergeArr[i] = secArr[i];
                } else {
                    mergeArr[i] = firArr[i - seclen];
                }
            }
        } else if (secArr[0] >= firArr[firlen - 1]) {
            for (int i = 0; i < mergeArr.length; i++) {
                if (i < firlen) {
                    mergeArr[i] = firArr[i];
                } else {
                    mergeArr[i] = secArr[i - firlen];
                }
            }
        } else {
            int indexFir = 0, indexSec = 0;
            for (int i = 0; i < mergeArr.length; i++) {
                if (indexFir < firlen && indexSec < seclen) {
                    if (firArr[indexFir] > secArr[indexSec]) {
                        mergeArr[i] = secArr[indexSec];
                        indexSec++;
                    } else if (firArr[indexFir] < secArr[indexSec]) {
                        mergeArr[i] = firArr[indexFir];
                        indexFir++;
                    }
                } else if (indexFir < firlen) {
                    mergeArr[i] = firArr[indexFir];
                    indexFir++;
                } else if (indexSec < seclen) {
                    mergeArr[i] = secArr[indexSec];
                    indexSec++;
                }
            }
        }
        return mergeArr;
    }
}
