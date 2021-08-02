package com.example.jiao.cityapplication;

import org.junit.Test;


public class MergeOrderArray2 {

    @Test
    public void getResult() {
        int[] a = {1, 2, 2, 3, 4, 5};
        int[] b = {5, 7, 9, 13};
        sort(a,b);
    }

/*    思路分析:关键点在于如何确定合并后数组c[i]处的值，应该取得a中的值还是b中的值，还有一个点需要考虑的是如何通过循环来遍历数组值进行比对及当数组长度不一致时，剩余值应该如何处理。

            1.比对策略

    很明显可以通过设置数组游标i，j,k来分别指示a,b,c数组中的元素,c[k]=min{a[i],b[j]},c[k]取当前i，j游标指示的两个数组值中的最小值，假设a[i]<b[j] ,应该将a[i]插进c[k]处，同时i，k游标应该后移一位;同样的,反之，应该将b[i]插进c[k]处，同时j,k游标后移。

            2.剩余值处理策略

    a,b两个数组长度很可能不一致，当游标 i或j前进至数组末尾时，数组长度较短的一方游标应该终止前进，否则将发生数组越界异常，而数组长度较长的一方游标应该继续前进，并逐个将剩余数字插入到合并后的数组中。*/

    /*算法时间复杂度为O(m+n)，空间复杂度也是O(m+n)*/

    public static void sort(int[] a, int[] b) {

        //获取数组长度
        int len_a = a.length;
        int len_b = b.length;
        int[] result = new int[len_a + len_b];
        int i, j, k;
        i = j = k = 0;
        while (i < len_a && j < len_b) {
            if (a[i] <= b[j])
                result[k++] = a[i++];
            else
                result[k++] = b[j++];
        }
        while (i < len_a)
            result[k++] = a[i++];
        while (j < len_b)
            result[k++] = b[j++];
        for (int p : result) {
            System.out.print(p + ",");
        }
    }

}
