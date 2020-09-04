package cn.lx.tensquare.article.service.impl;

import cn.lx.tensquare.article.dao.ArticleMapper;
import cn.lx.tensquare.article.pojo.Article;
import cn.lx.tensquare.article.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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


    /**
     * Article条件+分页查询
     * @param article 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Article> findPage(Article article, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(article);
        //执行搜索
        return new PageInfo<Article>(articleMapper.selectByExample(example));
    }

    /**
     * Article分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Article> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Article>(articleMapper.selectAll());
    }

    /**
     * Article条件查询
     * @param article
     * @return
     */
    @Override
    public List<Article> findList(Article article){
        //构建查询条件
        Example example = createExample(article);
        //根据构建的条件查询数据
        return articleMapper.selectByExample(example);
    }


    /**
     * Article构建查询对象
     * @param article
     * @return
     */
    public Example createExample(Article article){
        Example example=new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        if(article!=null){
            // ID
            if(!StringUtils.isEmpty(article.getId())){
                    criteria.andEqualTo("id",article.getId());
            }
            // 专栏ID
            if(!StringUtils.isEmpty(article.getColumnid())){
                    criteria.andEqualTo("columnid",article.getColumnid());
            }
            // 用户ID
            if(!StringUtils.isEmpty(article.getUserid())){
                    criteria.andEqualTo("userid",article.getUserid());
            }
            // 标题
            if(!StringUtils.isEmpty(article.getTitle())){
                    criteria.andLike("title","%"+article.getTitle()+"%");
            }
            // 文章正文
            if(!StringUtils.isEmpty(article.getContent())){
                    criteria.andEqualTo("content",article.getContent());
            }
            // 文章封面
            if(!StringUtils.isEmpty(article.getImage())){
                    criteria.andEqualTo("image",article.getImage());
            }
            // 发表日期
            if(!StringUtils.isEmpty(article.getCreatetime())){
                    criteria.andEqualTo("createtime",article.getCreatetime());
            }
            // 修改日期
            if(!StringUtils.isEmpty(article.getUpdatetime())){
                    criteria.andEqualTo("updatetime",article.getUpdatetime());
            }
            // 是否公开
            if(!StringUtils.isEmpty(article.getIspublic())){
                    criteria.andEqualTo("ispublic",article.getIspublic());
            }
            // 是否置顶
            if(!StringUtils.isEmpty(article.getIstop())){
                    criteria.andEqualTo("istop",article.getIstop());
            }
            // 浏览量
            if(!StringUtils.isEmpty(article.getVisits())){
                    criteria.andEqualTo("visits",article.getVisits());
            }
            // 点赞数
            if(!StringUtils.isEmpty(article.getThumbup())){
                    criteria.andEqualTo("thumbup",article.getThumbup());
            }
            // 评论数
            if(!StringUtils.isEmpty(article.getComment())){
                    criteria.andEqualTo("comment",article.getComment());
            }
            // 审核状态
            if(!StringUtils.isEmpty(article.getState())){
                    criteria.andEqualTo("state",article.getState());
            }
            // 所属频道
            if(!StringUtils.isEmpty(article.getChannelid())){
                    criteria.andEqualTo("channelid",article.getChannelid());
            }
            // URL
            if(!StringUtils.isEmpty(article.getUrl())){
                    criteria.andEqualTo("url",article.getUrl());
            }
            // 类型
            if(!StringUtils.isEmpty(article.getType())){
                    criteria.andEqualTo("type",article.getType());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        articleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Article
     * @param article
     */
    @Override
    public void update(Article article){
        articleMapper.updateByPrimaryKey(article);
    }

    /**
     * 增加Article
     * @param article
     */
    @Override
    public void add(Article article){
        articleMapper.insert(article);
    }

    /**
     * 根据ID查询Article
     * @param id
     * @return
     */
    @Override
    public Article findById(String id){
        return  articleMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Article全部数据
     * @return
     */
    @Override
    public List<Article> findAll() {
        return articleMapper.selectAll();
    }
}
