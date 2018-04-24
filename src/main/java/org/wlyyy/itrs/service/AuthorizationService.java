package org.wlyyy.itrs.service;

import org.wlyyy.common.domain.BaseServiceResponse;
import org.wlyyy.itrs.domain.UserAgent;

/**
 * 统一授权服务
 *
 * @author wly
 */
public interface AuthorizationService {

    BaseServiceResponse<UserAgent> login(String userName, String password);

    boolean isLogin(String sessionKey);

    boolean logout(String sessionKey);
}
