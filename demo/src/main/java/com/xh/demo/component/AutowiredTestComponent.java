package com.xh.demo.component;

import com.xh.interfaces.AutowiredTest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AutowiredTestComponent {

    @Autowired
    public AutowiredTestComponent(List<AutowiredTest> autowiredTestList) {
        this.autowiredTestList = autowiredTestList;
    }



//    @Autowired
//    public void setAutowiredTestList(List<AutowiredTest> autowiredTestList) {
//        this.autowiredTestList = autowiredTestList;
//    }

    @Getter
    private List<AutowiredTest> autowiredTestList;


    @Getter
    @Setter
    @Autowired
    private Map<String, AutowiredTest> autowiredTestMap;
}
