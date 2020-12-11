package com.xh.algorithm.leetcode;

import com.google.common.collect.Lists;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.TreeMap;

public class Q973 {
    public int[][] kClosest(int[][] points, int K) {
        TreeMap<Double, Integer> resultMap = new TreeMap<>((o1, o2) -> o2.compareTo(o1) >= 0 ? 1 : -1);

        for (int i = 0; i < points.length; i++) {
            double v = Math.pow(points[i][0], 2) + Math.pow(points[i][1], 2);
            resultMap.put(v, i);
        }

        ArrayList<Integer> resultList = Lists.newArrayList();

        for (int i = 0; i < K; i++) {
            resultList.add(resultMap.pollLastEntry().getValue());
        }

        int[][] ints = new int[resultList.size()][2];

        for (int i = 0; i < resultList.size(); i++) {
            ints[i][0] = points[resultList.get(i)][0];
            ints[i][1] = points[resultList.get(i)][1];
        }

        return ints;
    }

    public static void main(String[] args) {
        int[][] ints = new GsonBuilder().create().fromJson("[[3,3],[5,-1],[-2,4]]", int[][].class);
        int k = 2;
        int[][] result = new Q973().kClosest(ints, k);
        System.out.println(new GsonBuilder().create().toJson(result));
    }
}
