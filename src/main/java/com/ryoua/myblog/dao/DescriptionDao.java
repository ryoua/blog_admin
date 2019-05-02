package com.ryoua.myblog.dao;

import com.ryoua.myblog.entity.Description;

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
}
