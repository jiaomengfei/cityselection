package com.example.jiao.cityapplication;

import org.junit.Test;

public class BinarySearch {

    //概念：二分查找也称折半查找（Binary Search），它是一种效率较高的查找方法。但是，折半查找要求线性表必须采用顺序存储结构，而且表中元素按关键字有序排列。
    //思想：每次去找中间的那个元素比较大小，每次可以减少一半元素比较。

//    定义最小，中间，最大索引：min，mid，max
//            计算中间索引
//    那中间索引和查找元素索引比较，相等：返回中间索引；小于：左边查找 max = mid -1；大于：右边查找 min = mid + 1；
//    重新获取最小索引和最大索引
//    重复前面的步骤，直到找到匹配索引

//    复杂度
//    总共有n个元素，每次查找的区间大小就是n，n/2，n/4，…，n/2^k（接下来操作元素的剩余个数），其中k就是循环的次数。
//    由于n/2^k
//    取整后>=1，即令n/2^k=1，
//    可得k=log2n,（是以2为底，n的对数），所以
//    时间复杂度：可以表示O(n)=O(logn)


    public int binarySort(int Array[], int low, int high, int key) {
        if (low <= high) {
            int mid = (low + high) / 2;
            if (key == Array[mid]) {
                return mid;
            } else if (key < Array[mid]) {
                return binarySort(Array, low, mid - 1, key);
            } else {
                return binarySort(Array, mid + 1, high, key);
            }
        } else {
            return -1;
        }
    }

    public static int search(int[] arr, int value) {
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (arr[mid] > value) {
                max = mid - 1;// 比中间索引的数字大则关键字在左区域
            } else if (arr[mid] < value) {
                min = mid + 1;// 比中间索引的数字小则关键字在右区域
            } else {
                return mid; // 找到直接返回索引
            }
        }
        return -1; // 没找到,返回-1
    }


    @Test
    public void result() {
        int[] x = {1, 2, 4, 5, 6, 9};
        System.out.println(binarySort(x, 0, x.length - 1, 5));
        System.out.println(search(x,5));
    }

}
