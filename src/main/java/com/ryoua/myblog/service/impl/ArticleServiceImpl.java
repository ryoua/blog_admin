package com.ryoua.myblog.service.impl;

import com.ryoua.myblog.dao.ArticleDao;
import com.ryoua.myblog.entity.Article;
import com.ryoua.myblog.service.ArticleService;
import com.ryoua.myblog.utils.PageResult;
import com.ryoua.myblog.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author ryoua Created on 2019-05-07
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public PageResult getArticlePage(PageUtil pageUtil) {
        List<Article> articleList = articleDao.findArticles(pageUtil);
        int total = articleDao.getTotalArticles(pageUtil);
        PageResult pageResult = new PageResult(articleList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Article queryObject(Integer id) {
        return articleDao.getArticleById(id);
    }

    @Override
    public List<Article> queryList(Map<String, Object> map) {
        List<Article> articles = articleDao.findArticles(map);
        return articles;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return articleDao.getTotalArticles(map);
    }

    @Override
    public int save(Article article) {
        try {
            return articleDao.insertArticle(article);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Article article) {
        article.setUpdateTime(new Date());
        return articleDao.updArticle(article);
    }

    @Override
    public int delete(Integer id) {
        return articleDao.delArticle(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return articleDao.deleteBatch(ids);
    }

}
