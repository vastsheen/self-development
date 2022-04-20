package com.xh.algorithm.leetcode;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 * 限制：0 <= 数组长度 <= 50000
 */
public class Offer_Q51 {

    public int solution(int[] nums) {
        int[] temp = new int[nums.length];

        System.arraycopy(nums, 0, temp, 0 , nums.length);

        return mergeSortAndCalcReverse(temp, 0, temp.length - 1);
    }

    private int mergeSortAndCalcReverse(int[] temp, int start, int end) {
        int mid = (end - start) / 2 + start;

        final int left = mergeSortAndCalcReverse(temp, start, mid);
        final int right = mergeSortAndCalcReverse(temp, mid + 1, end);

        return 0;
    }
}
