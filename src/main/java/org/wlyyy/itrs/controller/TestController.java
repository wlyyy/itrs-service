package org.wlyyy.itrs.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wlyyy.common.domain.BasePageResponse;
import org.wlyyy.itrs.dao.UserMapper;
import org.wlyyy.itrs.domain.User;

import java.text.SimpleDateFormat;
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

    @RequestMapping("user/{id}")
    @ResponseBody
    public GetUserResponse getUser(@PathVariable("id") String id) {
        return new GetUserResponse(true, "Get success", dao.findById(Long.parseLong(id)));
    }

    /**
     * 页面层User返回对象
     */
    static class UserResponse {
        private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

        public static UserResponse buildByUser(User user) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            return new UserResponse(user.getId(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getSex(),
                    user.getDepartmentId(),
                    user.getRealName(),
                    simpleDateFormat.format(user.getGmtCreate()),
                    simpleDateFormat.format(user.getGmtModify())
            );
        }

        private final Long id;
        private final String userName;
        private final String email;

        private final Integer sex;
        private final Long departmentId;
        private final String realName;
        private final String gmtCreate;
        private final String gmtModify;

        public UserResponse(Long id, String userName, String email, Integer sex, Long departmentId, String realName, String gmtCreate, String gmtModify) {
            this.id = id;
            this.userName = userName;
            this.email = email;
            this.sex = sex;
            this.departmentId = departmentId;
            this.realName = realName;
            this.gmtCreate = gmtCreate;
            this.gmtModify = gmtModify;
        }

        public Long getId() {
            return id;
        }

        public String getUserName() {
            return userName;
        }

        public String getEmail() {
            return email;
        }

        public Integer getSex() {
            return sex;
        }

        public Long getDepartmentId() {
            return departmentId;
        }

        public String getRealName() {
            return realName;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public String getGmtModify() {
            return gmtModify;
        }
    }

    static class GetUserResponse extends BasePageResponse<UserResponse> {

        public GetUserResponse(boolean success, String message, User data) {
            super(success, message, UserResponse.buildByUser(data));
        }
    }
}
