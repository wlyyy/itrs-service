package org.wlyyy.itrs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wlyyy.common.domain.BaseRestResponse;
import org.wlyyy.itrs.domain.User;
import org.wlyyy.itrs.domain.UserAgent;
import org.wlyyy.itrs.service.AuthorizationService;

@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseRestResponse<UserAgent> login(String userName, String password) {

        return new BaseRestResponse<>(true, "登录成功", new UserAgent());
    }

    // TODO logout
    // TODO isLogin
    // TODO getToken?
}
