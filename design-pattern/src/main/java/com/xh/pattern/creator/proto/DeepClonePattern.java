package com.xh.pattern.creator.proto;

import com.google.common.collect.Lists;
import com.xh.pattern.model.CloneModel;

/**
 * 除了int是值比较，其他都是比较的对象地址。
 */
public class DeepClonePattern {
    public static void main(String[] args) {
        CloneModel obj = new CloneModel();
        obj.setAge(18);
        obj.setList(Lists.newArrayList("1", "g"));
        obj.setName("test");
        try {
            CloneModel clone = obj.deepClone();
            System.out.println(clone.getName() == obj.getName());
            System.out.println(clone.getAge() == obj.getAge());
            System.out.println(clone.getList() == obj.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
