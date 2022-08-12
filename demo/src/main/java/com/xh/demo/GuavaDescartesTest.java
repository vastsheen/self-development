package com.xh.demo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @Author：Joey
 * @Date: 2022/7/5
 * @Desc: guava笛卡尔积算法
 * 参考：https://www.cnblogs.com/rollenholt/p/3628362.html
 **/
public class GuavaDescartesTest {
    public static void main(String[] args) {
//        ImmutableSet<Character> charList = ImmutableSet.of('a', 'a', 'b', 'c');
//        Set<List<Character>> set = Sets.cartesianProduct(charList, charList, charList);
//        for (List<Character> characters : set) {
//            System.out.println(characters);
//        }

        List<List<String>> colorList = CollUtil.newArrayList(CollUtil.newArrayList("红色", "黑色"), CollUtil.newArrayList("金色"), CollUtil.newArrayList("红色"));
        List<List<String>> sizeList = Arrays.asList(CollUtil.newArrayList("32G"), CollUtil.newArrayList("64G"));
        List<List<String>> placeList = Arrays.asList(CollUtil.newArrayList("国产", "垃圾"), CollUtil.newArrayList("进口"));

        List<List<List<String>>> resultList = Lists.cartesianProduct(colorList, sizeList, placeList);
        Console.log(resultList.size());

        resultList.forEach(System.out::println);
    }
}