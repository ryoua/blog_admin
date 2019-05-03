package com.ryoua.myblog.service;

import com.ryoua.myblog.entity.AdminUser;

/**
 * @Author ryoua Created on 2019-05-03
 */
public interface AdminUserService {
    /**
     * 登陆功能
     *
     * @return
     */
    AdminUser updateTokenAndLogin(String userName, String password);

    /**
     * 根据userToken获取用户记录
     *
     * @return
     */
    AdminUser getAdminUserByToken(String userToken);
}
