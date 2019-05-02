package com.ryoua.myblog.service.impl;

import com.ryoua.myblog.dao.DescriptionDao;
import com.ryoua.myblog.entity.Description;
import com.ryoua.myblog.service.DescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ryoua Created on 2019-05-02
 */
@Service("descriptionService")
public class DescriptionServiceImpl implements DescriptionService {
    @Autowired
    private DescriptionDao descriptionDao;

    public Description getLastDescription() {
        return descriptionDao.getLastDescription();
    }
}
