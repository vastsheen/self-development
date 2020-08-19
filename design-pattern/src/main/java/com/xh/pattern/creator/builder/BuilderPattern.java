package com.xh.pattern.creator.builder;

import com.google.gson.GsonBuilder;
import com.xh.pattern.model.BuilderModel;

//import static cn.hutool.http.ContentType.JSON;

public class BuilderPattern {
    public static void main(String[] args) {
        BuilderModel test = new BuilderModel.InnerBuilder().setName("test").setNo(1).build();
        System.out.println(new GsonBuilder().create().toJson(test));
    }
}
