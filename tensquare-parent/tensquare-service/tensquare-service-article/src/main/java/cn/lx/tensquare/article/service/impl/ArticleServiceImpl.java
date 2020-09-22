package cn.lx.tensquare.article.service.impl;

import cn.lx.tensquare.article.dao.ArticleMapper;
import cn.lx.tensquare.article.pojo.Article;
import cn.lx.tensquare.article.service.ArticleService;
import cn.lx.tensquare.notice.feign.NoticeFeign;
import cn.lx.tensquare.notice.pojo.Notice;
import cn.lx.tensquare.utils.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private NoticeFeign noticeFeign;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * Article条件+分页查询
     *
     * @param article 查询条件
     * @param page    页码
     * @param size    页大小
     * @return 分页结果
     */
    @Override
    public Page<Article> findPage(Article article, int page, int size) {
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
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Article> findPage(int page, int size) {
        //静态分页
        Page<Article> page1 = new Page<>(page, size);
        //分页查询
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        Page<Article> pageInfo = (Page<Article>) articleMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * Article条件查询
     *
     * @param article
     * @return
     */
    @Override
    public List<Article> findList(Article article) {
        //构建查询条件
        QueryWrapper<Article> queryWrapper = createWrapper(article);
        //根据构建的条件查询数据
        return articleMapper.selectList(queryWrapper);
    }


    /**
     * Article构建查询对象
     *
     * @param article
     * @return
     */
    public QueryWrapper<Article> createWrapper(Article article) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        if (article != null) {
            // ID
            if (!StringUtils.isEmpty(article.getId())) {
                queryWrapper.eq("id", article.getId());
            }
            // 专栏ID
            if (!StringUtils.isEmpty(article.getColumnid())) {
                queryWrapper.eq("columnid", article.getColumnid());
            }
            // 用户ID
            if (!StringUtils.isEmpty(article.getUserid())) {
                queryWrapper.eq("userid", article.getUserid());
            }
            // 标题
            if (!StringUtils.isEmpty(article.getTitle())) {
                queryWrapper.eq("title", "%" + article.getTitle() + "%");
            }
            // 文章正文
            if (!StringUtils.isEmpty(article.getContent())) {
                queryWrapper.eq("content", article.getContent());
            }
            // 文章封面
            if (!StringUtils.isEmpty(article.getImage())) {
                queryWrapper.eq("image", article.getImage());
            }
            // 发表日期
            if (!StringUtils.isEmpty(article.getCreatetime())) {
                queryWrapper.eq("createtime", article.getCreatetime());
            }
            // 修改日期
            if (!StringUtils.isEmpty(article.getUpdatetime())) {
                queryWrapper.eq("updatetime", article.getUpdatetime());
            }
            // 是否公开
            if (!StringUtils.isEmpty(article.getIspublic())) {
                queryWrapper.eq("ispublic", article.getIspublic());
            }
            // 是否置顶
            if (!StringUtils.isEmpty(article.getIstop())) {
                queryWrapper.eq("istop", article.getIstop());
            }
            // 浏览量
            if (!StringUtils.isEmpty(article.getVisits())) {
                queryWrapper.eq("visits", article.getVisits());
            }
            // 点赞数
            if (!StringUtils.isEmpty(article.getThumbup())) {
                queryWrapper.eq("thumbup", article.getThumbup());
            }
            // 评论数
            if (!StringUtils.isEmpty(article.getComment())) {
                queryWrapper.eq("comment", article.getComment());
            }
            // 审核状态
            if (!StringUtils.isEmpty(article.getState())) {
                queryWrapper.eq("state", article.getState());
            }
            // 所属频道
            if (!StringUtils.isEmpty(article.getChannelid())) {
                queryWrapper.eq("channelid", article.getChannelid());
            }
            // URL
            if (!StringUtils.isEmpty(article.getUrl())) {
                queryWrapper.eq("url", article.getUrl());
            }
            // 类型
            if (!StringUtils.isEmpty(article.getType())) {
                queryWrapper.eq("type", article.getType());
            }
        }
        return queryWrapper;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        articleMapper.deleteById(id);
    }

    /**
     * 修改Article
     *
     * @param article
     */
    @Override
    public void update(Article article) {
        articleMapper.updateById(article);
    }

    /**
     * 增加Article,需要通知已订阅该作者的人
     *
     * @param article
     */
    @Override
    public void add(Article article) {
        //设置主键值
        article.setId(idWorker.nextId() + "");
        article.setCreatetime(new Date());
        article.setVisits(0);
        article.setThumbup(0);
        article.setComment(0);
        //设置审核状态0 待审核
        article.setState("0");
        articleMapper.insert(article);

        //获取需要通知的用户
        Set<String> members = redisTemplate.boundSetOps("author_subscribe_" + article.getUserid()).members();
        Notice notice = new Notice();
        //操作用户id
        notice.setOperatorId(article.getUserid());
        //操作类型（订阅）
        notice.setAction("publish");
        //被操作的对象
        notice.setTargetType("article");
        //被操作的对象的id
        notice.setTargetId(article.getId());
        //发表日期
        notice.setCreatetime(article.getCreatetime());
        //通知类型sys 系统通知
        notice.setType("sys");
        for (String member : members) {
            //消息接收者id
            notice.setReceiverId(member);
            noticeFeign.add(notice);
        }
        //向mq发送消息,向所有绑定了路由键为该作者id的队列发送消息，消息内容为文章id
        rabbitTemplate.convertAndSend("author_subscribe", article.getUserid(), article.getId());
    }

    /**
     * 根据ID查询Article
     *
     * @param id
     * @return
     */
    @Override
    public Article findById(String id) {
        return articleMapper.selectById(id);
    }

    /**
     * 查询Article全部数据
     *
     * @return
     */
    @Override
    public List<Article> findAll() {
        return articleMapper.selectList(null);
    }

    /**
     * 根据文章id订阅文章作者
     *
     * @param id
     * @param userid
     * @return true 订阅成功，false 取消订阅
     */
    @Override
    public Boolean subscribe(String id, String userid) {
        //根据文章id获取文章作者id
        Article article = articleMapper.selectById(id);
        String articleUserid = article.getUserid();
        /**
         * 使用rabbitmq发送通知消息
         */
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate.getConnectionFactory());
        //创建一个交换机(该交换机用来保存推送的消息）
        DirectExchange directExchange = new DirectExchange("author_subscribe");
        //声明该交换机
        rabbitAdmin.declareExchange(directExchange);
        //声明一个队列（用来保存需要推送到某个用户的消息）
        Queue queue = new Queue("author_subscribe_" + userid, true);
        //绑定交换机和队列,设置路由键为作者id
        Binding binding = BindingBuilder.bind(queue).to(directExchange).with(articleUserid);


        //判断用户是否已经订阅，双向保存
        //保存者作者的订阅者
        String authorKey = "author_subscribe_" + articleUserid;
        //保存者用户订阅的作者
        String userKey = "user_subscribe_" + userid;
        if (redisTemplate.boundSetOps(authorKey).isMember(userid) &&
                redisTemplate.boundSetOps(userKey).isMember(articleUserid)) {
            //已订阅,取消订阅
            redisTemplate.boundSetOps(authorKey).remove(userid);
            redisTemplate.boundSetOps(userKey).remove(articleUserid);
            //删除绑定的队列和路由键
            rabbitAdmin.removeBinding(binding);
            return false;
        }
        //未订阅,订阅
        redisTemplate.boundSetOps(authorKey).add(userid);
        redisTemplate.boundSetOps(userKey).add(articleUserid);
        //声明队列
        rabbitAdmin.declareQueue(queue);
        //声明绑定
        rabbitAdmin.declareBinding(binding);
        return true;
    }

    /**
     * 根据文章id点赞文章,点赞需要通知作者
     *
     * @param id     文章id
     * @param userid 点赞人的id
     * @return true 点赞成功，false 取消点赞
     */
    @Override
    public Boolean thumbup(String id, String userid) {
        //判断redis中是否已有点赞记录
        if (redisTemplate.boundSetOps("article_thumbup").isMember(id + "_" + userid)) {
            //已经点过赞，取消点赞
            redisTemplate.boundSetOps("article_thumbup").remove(id + "_" + userid);
            //mysql中记录数减一
            articleMapper.updateThumbup(id, -1);
            return false;
        }
        //没有点过赞，点赞
        redisTemplate.boundSetOps("article_thumbup").add(id + "_" + userid);
        //mysql中记录数加一
        articleMapper.updateThumbup(id, 1);


        //获取文章详情
        Article article = articleMapper.selectById(id);
        Notice notice = new Notice();
        //操作用户id(点赞的是当前用户）
        notice.setOperatorId(userid);
        //操作类型（点赞）
        notice.setAction("thumbup");
        //被操作的对象
        notice.setTargetType("article");
        //被操作的对象的id
        notice.setTargetId(article.getId());
        //发表日期
        notice.setCreatetime(article.getCreatetime());
        //通知类型sys 系统通知
        notice.setType("sys");
        //消息接收者id（要通知作者）
        notice.setReceiverId(article.getUserid());
        noticeFeign.add(notice);

        //通知作者
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate.getConnectionFactory());
        //不需要重新创建交换机这么麻烦
//        DirectExchange directExchange = new DirectExchange("article_thumbup");
//        rabbitAdmin.declareExchange(directExchange);
        Queue queue = new Queue("article_thumbup_" + article.getUserid(), true);
//        Binding binding = BindingBuilder.bind(queue).to(directExchange).with(article.getUserid());
        rabbitAdmin.declareQueue(queue);
//        rabbitAdmin.declareBinding(binding);
        //向mq发送消息,向所有绑定了路由键为该作者id的队列发送消息，消息内容为文章id
//        rabbitTemplate.convertAndSend("article_thumbup",article.getUserid(),article.getId());
        //向mq发送消息,不绑定的话routingkey就是队列名，消息内容为文章id
        rabbitTemplate.convertAndSend("article_thumbup_" + article.getUserid(), article.getId());
        return true;
    }
}
