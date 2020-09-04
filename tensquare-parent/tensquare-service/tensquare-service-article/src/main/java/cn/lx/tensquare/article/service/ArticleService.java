package cn.lx.tensquare.article.service;

import cn.lx.tensquare.article.pojo.Article;
import com.github.pagehelper.PageInfo;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Article业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface ArticleService {

    /***
     * Article多条件分页查询
     * @param article
     * @param page
     * @param size
     * @return
     */
    PageInfo<Article> findPage(Article article, int page, int size);

    /***
     * Article分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Article> findPage(int page, int size);

    /***
     * Article多条件搜索方法
     * @param article
     * @return
     */
    List<Article> findList(Article article);

    /***
     * 删除Article
     * @param id
     */
    void delete(String id);

    /***
     * 修改Article数据
     * @param article
     */
    void update(Article article);

    /***
     * 新增Article
     * @param article
     */
    void add(Article article);

    /**
     * 根据ID查询Article
     * @param id
     * @return
     */
     Article findById(String id);

    /***
     * 查询所有Article
     * @return
     */
    List<Article> findAll();
}
