package cn.lx.tensquare.article.service;

import cn.lx.tensquare.article.pojo.Article;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/****
 * @Author:lx
 * @Description:Article业务层接口
 * @Date 2020/9/5 10:33
 *****/
public interface ArticleService {

    /***
     * Article多条件分页查询
     * @param article
     * @param page
     * @param size
     * @return
     */
    Page<Article> findPage(Article article, int page, int size);

    /***
     * Article分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Article> findPage(int page, int size);

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

    /**
     * 根据文章id订阅文章作者
     * @param id
     * @param userid
     * @return true 订阅成功，false 取消订阅
     */
    Boolean subscribe(String id, String userid);

    /**
     * 根据文章id点赞文章
     * @param id
     * @param userid
     * @return  true 点赞成功，false 取消点赞
     */
    Boolean thumbup(String id, String userid);
}
