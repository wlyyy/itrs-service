package org.abomb4.itrs.controller;

import com.google.gson.Gson;
import org.abomb4.itrs.dao.TextMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TextMapper dao;

    @RequestMapping("test")
    public String xx() {
        return new Gson().toJson(dao.getById(1L));
    }
}
