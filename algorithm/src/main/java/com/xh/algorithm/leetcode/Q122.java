package com.xh.algorithm.leetcode;

public class Q122 {
    public int maxProfit(int[] prices) {
        int sum = 0;
        //遍历数组
        for (int i = 0; i < prices.length; i++) {
            // 遍历当前数组之后的元素
            int currentValue = prices[i];
            int j = 0;
            int offsetValue = currentValue;
            for (; j  + i + 1 < prices.length; j ++) {
                if (offsetValue < prices[i + j +1]) {
                    offsetValue = prices[i + j + 1];
                    continue;
                }
                break;
            }
            // 说明当前数比后续的大 直接遍历下位
            if (j == 0) {
                continue;
            }
            sum += prices[i + j] - currentValue;
            // 此轮计算完毕之后进行索引偏移
            i = i + j;
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(new Q122().maxProfit(new int[]{7,1,5,3,6,4}));
    }
}
