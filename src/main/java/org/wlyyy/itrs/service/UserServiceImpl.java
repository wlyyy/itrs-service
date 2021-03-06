package org.wlyyy.itrs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wlyyy.common.domain.BaseServicePageableRequest;
import org.wlyyy.common.domain.BaseServicePageableResponse;
import org.wlyyy.common.domain.BaseServiceResponse;
import org.wlyyy.common.utils.PageableUtils;
import org.wlyyy.itrs.dao.UserRepository;
import org.wlyyy.itrs.domain.User;
import org.wlyyy.itrs.request.UserQuery;
import org.wlyyy.itrs.utils.SecurityUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository dao;

    @Override
    public BaseServicePageableResponse<User> findByCondition(BaseServicePageableRequest<UserQuery> request) {

        final Pageable pageable = PageableUtils.getPageable(request);
        final List<User> queryResult = dao.findByCondition(request.getData(), pageable);

        final long count;
        if (queryResult.size() < request.getPageSize()) {
            count = queryResult.size();
        } else {
            count = dao.countByCondition(request.getData());
        }

        return new BaseServicePageableResponse<>(
                true, "Query success", queryResult,
                request.getPageNo(), request.getPageSize(), count
        );
    }

    @Override
    public User findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public BaseServiceResponse<User> validateUser(String userName, String password) {
        final User fullUserInfo = dao.findFullByUserName(userName);
        if (fullUserInfo == null) {
            return new BaseServiceResponse<>(false, "User not found.", null, null);
        }

        final String realPassword = fullUserInfo.getPassword();
        final String salt = fullUserInfo.getSalt();

        final String encrypt = SecurityUtils.encrypyPassword(password, salt);

        fullUserInfo.setPassword(null);
        fullUserInfo.setSalt(null);

        if (encrypt.equals(realPassword)) {
            return new BaseServiceResponse<>(true, "Validate successful.", fullUserInfo, null);
        } else {
            return new BaseServiceResponse<>(false, "Password incorrect.", null, null);
        }
    }

    @Override
    public BaseServiceResponse<User> createUser(User user) {
        user.setSalt(SecurityUtils.generateSalt());
        final String encrypyPassword = SecurityUtils.encrypyPassword(user.getPassword(), user.getSalt());
        user.setPassword(encrypyPassword);
        dao.insert(user);
        return new BaseServiceResponse<>(true, "Create user successfully.", null, null);
    }
}
