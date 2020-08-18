package com.xh.algorithm.sort;

import java.util.Arrays;

public class QuickSort {

    private static void sort(int[] arr, int low, int high){
        int l = low;
        int h = high;
        if (low >= high) {
            return;
        }

        int key = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= key) {
                high --;
            }
//            if (low < high) {
            arr[low ++] = arr[high];
//            }

            while (low < high && arr[low] <= key) {
                low ++;
            }
//            if (low <high) {
            arr[high --] = arr[low];
//            }

            System.out.println("a:"+Arrays.toString(arr));
        }
        arr[low] = key;
        sort(arr, l, low - 1);
        sort(arr, low +1 , h);
    }

    private static void quickSort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] a = {49, 38, 65, 97, 76, 13, 27, 49};
        quickSort(a);
        System.out.println("a:"+Arrays.toString(a));

        int[] b = {49, 38, 65, 97, 76, 13, 27, 49};
        quickSort(b, 0, a.length-1);
        System.out.println("b:"+Arrays.toString(b));
    }

    public static void quickSort(int[] array,int lo ,int hi){
        if(lo>=hi){
            return ;
        }
        //进行第一轮排序获取分割点
        int index=partition(array,lo,hi);
        //排序前半部分
        quickSort(array, lo, index - 1);
        //排序后半部分
        quickSort(array,index+1,hi);
    }

    /**
     * 一次快速排序
     * @param array 数组
     * @param lo 数组的前下标
     * @param hi 数组的后下标
     * @return key的下标index，也就是分片的间隔点
     */
    public static int partition(int []array,int lo,int hi) {
        /** 固定的切分方式 */
        int key = array[lo];//选取了基准点
        while (lo < hi) {
            //从后半部分向前扫描
            while (array[hi] >= key && hi > lo) {
                hi--;
            }
            array[lo] = array[hi];
            //从前半部分向后扫描
            while (array[lo] <= key && hi > lo) {
                lo++;
            }
            array[hi] = array[lo];
            System.out.println("b:"+Arrays.toString(array));
        }
        array[hi] = key;//最后把基准存入
        return hi;
    }
}
