package com.ryoua.myblog.service;

import com.ryoua.myblog.entity.Article;
import com.ryoua.myblog.utils.PageResult;
import com.ryoua.myblog.utils.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * @Author ryoua Created on 2019-05-07
 */
public interface ArticleService {

    PageResult getArticlePage(PageUtil pageUtil);

    Article queryObject(Integer id);

    List<Article> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int save(Article article);

    int update(Article article);

    int delete(Integer id);

    int deleteBatch(Integer[] ids);
}
