package com.ryoua.myblog.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author ryoua Created on 2019-05-02
 */
public class Description implements Serializable {
    private Long id;

    private String description;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Description{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
