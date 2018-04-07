package com.example.jiao.cityapplication;

/**
 * Created by jiao3 on 2018/3/17.
 */

public class QuickSort {
    /*介绍：
    快速排序是由东尼·霍尔所发展的一种排序算法。在平均状况下，排序 n 个项目要Ο(n log n)次比较。
    在最坏状况下则需要Ο(n2)次比较，但这种状况并不常见。
    事实上，快速排序通常明显比其他Ο(n log n) 算法更快，
    因为它的内部循环(inner loop)可以在大部分的架构上很有效率地被实现出来，
    且在大部分真实世界的数据，可以决定设计的选择，减少所需时间的二次方项之可能性。
    步骤：
    从数列中挑出一个元素，称为 "基准"(pivot)，重新排序数列，所有元素比基准值小的摆放在基准前面，
    所有元素比基准值大的摆在基准的后面(相同的数可以到任一边)。
    在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区(partition)操作。
    递归地(recursive)把小于基准值元素的子数列和大于基准值元素的子数列排序。*/
    //快速排序
    private void quickSort(int[]  a ,int left,int right) {
        if(left < right){
            int i,j,t,temp;
            temp=a[left]; //temp中存的就是基准数
            i=left;
            j=right;
            while(i!=j)
            {
                //顺序很重要，要先从右边开始找
                while(a[j]>=temp && i<j)
                    j--;
                //再找右边的
                while(a[i]<=temp && i<j)
                    i++;
                //交换两个数在数组中的位置
                if(i<j)
                {
                    t=a[i];
                    a[i]=a[j];
                    a[j]=t;
                }
            }
            //最终将基准数归位
            a[left]=a[i];
            a[i]=temp;
            quickSort(a,left,i-1);//继续处理左边的，这里是一个递归的过程
            quickSort(a,i+1,right);//继续处理右边的 ，这里是一个递归的过程
        }
    }

}
