package com.xh.pattern.creator.proto;

import com.google.common.collect.Lists;
import com.xh.pattern.model.CloneModel;
import lombok.Data;

/**
 * 浅克隆 新对象的属性是旧对象中的引用 即持有的属性对象是同一个对象
 */
@Data
public class ShallowClonePattern implements Cloneable{
    public static void main(String[] args) {
        CloneModel obj = new CloneModel();
        obj.setAge(18);
        obj.setList(Lists.newArrayList("1", "g"));
        obj.setName("test");
        try {
            CloneModel clone = (CloneModel) obj.clone();
            System.out.println(clone.getName() == obj.getName());
            System.out.println(clone.getAge() == obj.getAge());
            System.out.println(clone.getList() == obj.getList());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
