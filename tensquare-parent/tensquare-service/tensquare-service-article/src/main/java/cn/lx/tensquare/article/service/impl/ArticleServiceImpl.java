package cn.lx.tensquare.article.service.impl;

import cn.lx.tensquare.article.dao.ArticleMapper;
import cn.lx.tensquare.article.pojo.Article;
import cn.lx.tensquare.article.service.ArticleService;
import cn.lx.tensquare.utils.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Article业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private IdWorker idWorker;


    /**
     * Article条件+分页查询
     * @param article 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Article> findPage(Article article, int page, int size){
        //分页
        Page<Article> page1 = new Page<>(page, size);
        //搜索条件构建
        QueryWrapper<Article> queryWrapper = createWrapper(article);
        //执行搜索
        Page<Article> pageInfo = (Page<Article>) articleMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * Article分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Article> findPage(int page, int size){
        //静态分页
        Page<Article> page1 = new Page<>(page, size);
        //分页查询
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        Page<Article> pageInfo = (Page<Article>) articleMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * Article条件查询
     * @param article
     * @return
     */
    @Override
    public List<Article> findList(Article article){
        //构建查询条件
        QueryWrapper<Article> queryWrapper = createWrapper(article);
        //根据构建的条件查询数据
        return articleMapper.selectList(queryWrapper);
    }


    /**
     * Article构建查询对象
     * @param article
     * @return
     */
    public QueryWrapper<Article> createWrapper(Article article){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if(article!=null){
            // ID
            if(!StringUtils.isEmpty(article.getId())){
                    queryWrapper.eq("id",article.getId());
            }
            // 专栏ID
            if(!StringUtils.isEmpty(article.getColumnid())){
                    queryWrapper.eq("columnid",article.getColumnid());
            }
            // 用户ID
            if(!StringUtils.isEmpty(article.getUserid())){
                    queryWrapper.eq("userid",article.getUserid());
            }
            // 标题
            if(!StringUtils.isEmpty(article.getTitle())){
                    queryWrapper.eq("title","%"+article.getTitle()+"%");
            }
            // 文章正文
            if(!StringUtils.isEmpty(article.getContent())){
                    queryWrapper.eq("content",article.getContent());
            }
            // 文章封面
            if(!StringUtils.isEmpty(article.getImage())){
                    queryWrapper.eq("image",article.getImage());
            }
            // 发表日期
            if(!StringUtils.isEmpty(article.getCreatetime())){
                    queryWrapper.eq("createtime",article.getCreatetime());
            }
            // 修改日期
            if(!StringUtils.isEmpty(article.getUpdatetime())){
                    queryWrapper.eq("updatetime",article.getUpdatetime());
            }
            // 是否公开
            if(!StringUtils.isEmpty(article.getIspublic())){
                    queryWrapper.eq("ispublic",article.getIspublic());
            }
            // 是否置顶
            if(!StringUtils.isEmpty(article.getIstop())){
                    queryWrapper.eq("istop",article.getIstop());
            }
            // 浏览量
            if(!StringUtils.isEmpty(article.getVisits())){
                    queryWrapper.eq("visits",article.getVisits());
            }
            // 点赞数
            if(!StringUtils.isEmpty(article.getThumbup())){
                    queryWrapper.eq("thumbup",article.getThumbup());
            }
            // 评论数
            if(!StringUtils.isEmpty(article.getComment())){
                    queryWrapper.eq("comment",article.getComment());
            }
            // 审核状态
            if(!StringUtils.isEmpty(article.getState())){
                    queryWrapper.eq("state",article.getState());
            }
            // 所属频道
            if(!StringUtils.isEmpty(article.getChannelid())){
                    queryWrapper.eq("channelid",article.getChannelid());
            }
            // URL
            if(!StringUtils.isEmpty(article.getUrl())){
                    queryWrapper.eq("url",article.getUrl());
            }
            // 类型
            if(!StringUtils.isEmpty(article.getType())){
                    queryWrapper.eq("type",article.getType());
            }
        }
        return queryWrapper;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        articleMapper.deleteById(id);
    }

    /**
     * 修改Article
     * @param article
     */
    @Override
    public void update(Article article){
        articleMapper.updateById(article);
    }

    /**
     * 增加Article
     * @param article
     */
    @Override
    public void add(Article article){
        //设置主键值
        article.setId(idWorker.nextId()+"");
        articleMapper.insert(article);
    }

    /**
     * 根据ID查询Article
     * @param id
     * @return
     */
    @Override
    public Article findById(String id){
        return  articleMapper.selectById(id);
    }

    /**
     * 查询Article全部数据
     * @return
     */
    @Override
    public List<Article> findAll() {
        return articleMapper.selectList(null);
    }
}
