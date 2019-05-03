package com.ryoua.myblog.dao;

import com.ryoua.myblog.entity.AdminUser;
import com.ryoua.myblog.entity.Description;
import org.apache.ibatis.annotations.Param;

/**
 * @Author ryoua Created on 2019-05-02
 */
public interface DescriptionDao {
    /**
     * 获取最新一条描述
     *
     * @return
     */
    Description getLastDescription();

    /**
     * 根据登录名和密码获取用户记录
     *
     * @return
     */
    AdminUser getAdminUserByUserNameAndPassword(@Param("userName") String userName, @Param("passwordMD5") String passwordMD5);

    /**
     * 根据userToken获取用户记录
     *
     * @return
     */
    AdminUser getAdminUserByToken(String userToken);

    /**
     * 更新用户token值
     *
     * @param userId
     * @param newToken
     * @return
     */
    int updateUserToken(@Param("userId") Long userId, @Param("newToken") String newToken);
}
