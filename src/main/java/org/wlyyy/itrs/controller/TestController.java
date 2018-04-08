package org.wlyyy.itrs.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wlyyy.itrs.dao.UserMapper;
import org.wlyyy.itrs.domain.User;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UserMapper dao;

    @RequestMapping("test")
    public Map<String, String> xx() {
        final Map<String, String> map = new HashMap<>();
        LOG.info("■■■■■■■■■■ get方法 ■■■■■■■■■■■■");
        map.put("get", new Gson().toJson(dao.findById(1L)));
        LOG.info("■■■■■■■■■■ insert方法 ■■■■■■■■■■■■");
        User user = new User();

        user.setId(1L);
        user.setUserName("userName");
        user.setEmail("wlyyy@163.com");
        user.setPassword("password");
        user.setSalt("salt");
        user.setSex(1);
        user.setDepartmentId(1L);
        user.setRealName("realName");

        dao.insert(user);
        map.put("insert", "INSERT SUCCESS");

        LOG.info("■■■■■■■■■■ modify方法 ■■■■■■■■■■■■");
        final int affected = dao.updateById(user);
        map.put("update", "UPDATE MAY SUCCESS " + affected);

        return map;
    }
}
