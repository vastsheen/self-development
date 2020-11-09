package com.xh.algorithm.heap;

import java.util.Arrays;

/**
 * 最小堆练习
 */
public class MinHeap {

    Integer[] nums;

    int eleSize = 0;

    public MinHeap(int size) {
        nums = new Integer[size];
        Arrays.fill(nums, null);
    }

    public void add(int ele) {
        nums[eleSize] = ele;
        modify(eleSize);
        eleSize++;
    }

    private void modify(int childIndex) {
        if (childIndex == 0) {
            return;
        }
        int parentIndex = (childIndex - 1) / 2;
        while (nums[parentIndex] > nums[childIndex]) {
            int temp = nums[parentIndex];
            nums[parentIndex] = nums[childIndex];
            nums[childIndex] = temp;

            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;

            if (parentIndex < 0) {
                break;
            }
        }
    }


    /**
     * 上浮调整 * @param array     待调整的堆
     */
    public static void upAdjust(int[] array) {
        int childIndex = array.length - 1;
        int parentIndex = (childIndex - 1) / 2;    // temp保存插入的叶子节点值，用于最后的赋值
        int temp = array[childIndex];
        while (childIndex > 0 && temp < array[parentIndex]) {        //无需真正交换，单向赋值即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        array[childIndex] = temp;
    }

    /**
     * 下沉调整 * @param array     待调整的堆 * @param parentIndex
     * 要下沉的父节点 * @param parentIndex    堆的有效大小
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        // temp保存父节点值，用于最后的赋值
        int temp = array[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            // 如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }
            // 如果父节点小于任何一个孩子的值，直接跳出
            if (temp <= array[childIndex])
                break;
            //无需真正交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
            array[parentIndex] = temp;
        }
    }

    /**
     * 构建堆 * @param array     待调整的堆
     */
    public static void buildHeap(int[] array) {
        // 从最后一个非叶子节点开始，依次下沉调整
        for (int i = array.length / 2; i >= 0; i--) {
            downAdjust(array, i, array.length - 1);
        }
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(20);
        minHeap.add(10);
        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(8);
        minHeap.add(6);
        System.out.println(Arrays.toString(minHeap.nums));
        minHeap.add(4);
        minHeap.add(4);
        minHeap.add(3);
        minHeap.add(9);
        System.out.println(Arrays.toString(minHeap.nums));
        minHeap.add(2);
        minHeap.add(1);
        System.out.println(Arrays.toString(minHeap.nums));
        Integer r = 0;
        while ((r = minHeap.remove()) != null) {
            System.out.println(r);
            System.out.println(Arrays.toString(minHeap.nums));
        }

        System.out.println(Arrays.toString(minHeap.nums));

//        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
//        upAdjust(array);
//        System.out.println(Arrays.toString(array));
//        array = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
//        buildHeap(array);
//        System.out.println(Arrays.toString(array));
    }

    public Integer remove() {
        if (eleSize == 0) {
            return null;
        }
        int index = 0;

        Integer num = nums[index];
        if (eleSize == 1) {
            nums[index] = null;
            return num;
        }
        Integer movedEle = nums[eleSize - 1];
        nums[index] = movedEle;
        int child1Index = 1;
        int child2Index = 2;
        while (nums[child1Index] != null || nums[child2Index] != null) {
            int targetIndex = child1Index;
            if (nums[child1Index] == null || (nums[child2Index] != null && nums[child2Index] < nums[child1Index])) {
                targetIndex = child2Index;
            }
            if (nums[targetIndex] >= nums[index]) {
                break;
            }
            Integer temp = nums[targetIndex];
            nums[targetIndex] = nums[index];
            nums[index] = temp;
            index = targetIndex;
            child1Index = index * 2 +1;
            child2Index = index * 2 +2;
        }
        nums[eleSize - 1] = null;
        eleSize --;
//
        return num;
    }


//    nums[eleSize - 1] = null;
//        if (eleSize == 1) {
//            return num;
//        }
//        nums[index] = movedEle;
//        int child1Index = 1;
//        int child2Index = 2;
//        while (nums[child1Index] != null || nums[child2Index] != null &&
//                (nums[index] > nums[child1Index] || nums[index] > nums[child2Index])) {
//            int usefulIndex = child1Index;
//            if (nums[child1Index] > nums[child2Index]) {
//                usefulIndex = child2Index;
//            }
//            int temp = nums[usefulIndex];
//            nums[usefulIndex] = movedEle;
//            nums[index] = temp;
//            index = usefulIndex;
//            child1Index = index * 2 + 1;
//            child2Index = index * 2 + 2;
//            if (child2Index > eleSize - 1) {
//                break;
//            }
//        }
//        eleSize--;
}

