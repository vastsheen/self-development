package com.xh.demo.controller;

import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Api(tags="hello world类")
@RestController
public class HelloController {

    @GetMapping("/hello")
    @ApiOperation(value = "接口名", notes = "接口描述", httpMethod = "GET")
    public Object String () {
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("res", "world");
        return map;
    }
}
