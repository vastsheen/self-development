package com.xh.algorithm.exercise;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * test test.
 */
public class RectangleSolution {

    /**
     * @param matrix adfadf
     * @return asdasdasd
     */
    public int maximalRectangle(final char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[] l = new int[n];
        int[] r = new int[n];
        int[] h = new int[n];
        int result = 0;

        for (int i = 0; i < n; i++) {
            l[i] = 0;
            r[i] = n;
            h[i] = 0;
        }

        for (int i = 0; i < m; i++) {
//            System.out.print("h\t");
            int curLeft = 0, curRight = n;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    h[j] += 1;
                } else {
                    h[j] = 0;
                }
//                System.out.print(h[j]);
//                System.out.print(" ");
            }
//            System.out.print("\tl\t");
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    l[j] = Math.max(l[j], curLeft);
                } else {
                    l[j] = 0;
                    curLeft = j + 1;
                }
//                System.out.print(l[j]);
//                System.out.print(" ");
            }
//            System.out.print("\tr\t");
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    r[j] = Math.min(r[j], curRight);
                } else {
                    r[j] = n;
                    curRight = j;
                }
//                System.out.print(r[j] / 10 > 0 ? r[j] : " " + r[j]);
//                System.out.print(" ");
            }

            System.out.println(Arrays.toString(l));
            System.out.println(Arrays.toString(r));
            System.out.println(Arrays.toString(h));
            for (int j = 0; j < n; j++) {
                result = Math.max(result, (r[j] - l[j]) * h[j]);
            }
            System.out.println(result);
//            System.out.println("\ntemp result:" + result);
        }

        return result;
    }

    public static void main(String[] args) {
        final ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        System.out.println(Arrays.toString(integers.subList(1,-1).toArray()));
        System.out.println(Arrays.toString(integers.toArray()));


        char[][] matrix = new char[10][10];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = '0';
            }
        }

//        matrix[2][3] = '1';
//        matrix[2][4] = '1';
//        matrix[2][5] = '1';
//        matrix[2][6] = '1';
//        matrix[3][4] = '1';
        matrix[3][5] = '1';
        matrix[3][6] = '1';
//        matrix[3][7] = '1';
        matrix[2][5] = '1';
        matrix[2][4] = '1';
        matrix[2][1] = '1';
        matrix[2][6] = '1';
//        matrix[2][7] = '1';
//        matrix[2][8] = '1';
//        matrix[2][8] = '1';

//        printArray(matrix);

//        System.out.println("result :" + new RectangleSolution().maximalRectangle(matrix));
    }

    public static void  printArray(final char[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
