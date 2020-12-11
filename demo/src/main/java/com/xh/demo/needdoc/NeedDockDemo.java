package com.xh.demo.needdoc;

import com.xh.annotations.NeedDoc;
import org.springframework.stereotype.Component;

@Component
@NeedDoc
public class NeedDockDemo {

    public String test() {
        return "static";
    }
}
