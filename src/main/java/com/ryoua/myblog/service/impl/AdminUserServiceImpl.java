package com.ryoua.myblog.service.impl;

import com.ryoua.myblog.entity.AdminUser;
import com.ryoua.myblog.service.AdminUserService;
import org.springframework.stereotype.Service;

/**
 * @Author ryoua Created on 2019-05-03
 */
@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService {
    public AdminUser updateTokenAndLogin(String userName, String password) {
        return null;
    }

    public AdminUser getAdminUserByToken(String userToken) {
        return null;
    }
}
