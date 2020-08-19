package com.xh.pattern.model;

import lombok.Data;

import java.io.*;
import java.util.List;

@Data
public class CloneModel implements Cloneable, Serializable{

    private String name;
    private int age;
    private List<Object> list;

    @Override
    public CloneModel clone() throws CloneNotSupportedException {
        return (CloneModel)super.clone();
    }

    public CloneModel deepClone() throws IOException, ClassNotFoundException {
        //将对象写入流中
        ByteArrayOutputStream bao=new  ByteArrayOutputStream();

        ObjectOutputStream oos=new  ObjectOutputStream(bao);

        oos.writeObject(this);

        //将对象从流中取出

        ByteArrayInputStream bis=new  ByteArrayInputStream(bao.toByteArray());

        ObjectInputStream ois=new  ObjectInputStream(bis);

        return  (CloneModel)ois.readObject();
    }
}
