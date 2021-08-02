package com.example.jiao.cityapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ArrayNoRepeat {

    //第一种方式:最开始想到的是利用Set集合的不可重复性进行元素过滤
    @Test
    public void getArrayNoRepeat1() {
        int[] array = {1, 2, 2, 3, 4, 4, 5};
        Set set = new HashSet();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
        System.out.println(Arrays.toString(set.toArray()));
    }

    //第二种方式:要想保持原数组的顺序就使用有顺序、不重复特点的链表的哈希集合
    @Test
    public void getArrayNoRepeat2() {
        int[] array = {1, 2, 2, 3, 4, 4, 5};
        LinkedHashSet<Object> temp = new LinkedHashSet<>();
        for (int i = 0; i < array.length; i++) {
            temp.add(array[i]);
        }
        System.out.println(Arrays.toString(temp.toArray()));
    }

    //第三种方式:创建一个list集合，然后遍历数组将元素放入集合，再用contains()方法判断一个集合中是否已存在该元素即可
    @Test
    public void getArrayNoRepeat3() {
        int[] array = {1, 2, 2, 3, 4, 4, 5};
        List list = new ArrayList();
        for (int i = 0; i < array.length; i++) {
            if (!list.contains(array[i])) {
                list.add(array[i]);
            }
        }
        System.out.println(Arrays.toString(list.toArray()));
    }

    //第四种方式:两层循环遍历原数组，然后逐个判断是否和之后的元素重复，同时设立一个标记，用来分辨是否重复，根据标记将不重复的元素存入新数组
    @Test
    public void getArrayNoRepeat4() {
        int[] arr = {1, 2, 2, 3, 4, 4, 5};
        int t = 0;
        //临时数组
        Object[] xinArr = new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            boolean isRepeat = true;
            for (int j = i + 1; j < arr.length; j++) {
                //如果有重复元素，将标记置为false
                if (arr[i] == arr[j]) {
                    isRepeat = false;
                    break;
                }
            }
            //标记为true表示没有重复元素
            if (isRepeat) {
                xinArr[t] = arr[i];
                t++;
            }

        }
        System.out.println(Arrays.toString(xinArr));
        Object[] newArr = new Object[t];
        System.arraycopy(xinArr,0,newArr,0,t);
        System.out.println(Arrays.toString(newArr));

    }

}
