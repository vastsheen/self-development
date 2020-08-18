package com.xh.algorithm.exercise;

public class FindNearlyResult {

    private static int bestLen = 0,curLen = 0,curSum = 0,bestSum = 0,diff=1<<30;

    public static void main(String[] args) {
        int count = 3;
        int result = 100;
        int[] arr = new int[]{50,49,38};
        int[] cur = new int[count];
        int[] best = new int[count];

        for (int i = 0;i < count ;i++) {
            find(0,i, count, arr, cur,best, result);
        }
    }

    private static void find(int depth, int index, int count, int[] arr, int[] cur, int[] best, int result) {
        if (depth == count) {
            return;
        }
        curLen = depth + 1;
        cur[depth] = index;
        curSum += arr[index];
        for (int i = 0;i < curLen;i++) {
            System.out.print(cur[i] + " ");
        }
        System.out.println(curSum);
        if(curSum > result) {
            if( curSum-result < diff) {
                diff = curSum-result;
                for(int i=0;i<curLen;i++) {
                    best[i] = cur[i];
                }
                bestLen = curLen;
                bestSum = curSum;
            }
        } else {
            for(int i=0;i<count;i++) {
                boolean flag = false;
                for(int j=0;j<curLen;j++) {
                    if(cur[j] == i)  {
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    find(0, depth+1,i, arr, arr, cur, result);
                }
            }
        }
        curSum -= arr[index];
    }
}
