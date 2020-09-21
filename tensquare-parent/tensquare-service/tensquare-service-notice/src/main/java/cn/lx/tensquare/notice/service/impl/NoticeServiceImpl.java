package cn.lx.tensquare.notice.service.impl;
import cn.lx.tensquare.article.feign.ArticleFeign;
import cn.lx.tensquare.article.pojo.Article;
import cn.lx.tensquare.comment.feign.CommentFeign;
import cn.lx.tensquare.entity.Result;
import cn.lx.tensquare.notice.dao.NoticeFreshMapper;
import cn.lx.tensquare.notice.dao.NoticeMapper;
import cn.lx.tensquare.notice.pojo.Notice;
import cn.lx.tensquare.notice.pojo.NoticeFresh;
import cn.lx.tensquare.notice.service.NoticeService;
import cn.lx.tensquare.user.feign.UserFeign;
import cn.lx.tensquare.user.pojo.User;
import cn.lx.tensquare.utils.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:Notice业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private NoticeFreshMapper noticeFreshMapper;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private CommentFeign commentFeign;

    @Autowired
    private ArticleFeign articleFeign;


    /**
     * Notice条件+分页查询
     * @param notice 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Notice> findPage(Notice notice, int page, int size){
        //分页
        Page<Notice> page1 = new Page<>(page, size);
        //搜索条件构建
        QueryWrapper<Notice> queryWrapper = createWrapper(notice);
        //执行搜索
        Page<Notice> pageInfo = (Page<Notice>) noticeMapper.selectPage(page1, queryWrapper);
        List<Notice> records = pageInfo.getRecords();
        for (Notice record : records) {
            getOtherNoticeInfo(record);
        }
        pageInfo.setRecords(records);
        return pageInfo;
    }

    /**
     * Notice分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Notice> findPage(int page, int size){
        //静态分页
        Page<Notice> page1 = new Page<>(page, size);
        //分页查询
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        Page<Notice> pageInfo = (Page<Notice>) noticeMapper.selectPage(page1, queryWrapper);
        List<Notice> records = pageInfo.getRecords();
        for (Notice record : records) {
            getOtherNoticeInfo(record);
        }
        pageInfo.setRecords(records);
        return pageInfo;
    }

    /**
     * Notice条件查询
     * @param notice
     * @return
     */
    @Override
    public List<Notice> findList(Notice notice){
        //构建查询条件
        QueryWrapper<Notice> queryWrapper = createWrapper(notice);
        //根据构建的条件查询数据
        List<Notice> notices = noticeMapper.selectList(queryWrapper);
        for (Notice notice1 : notices) {
            getOtherNoticeInfo(notice1);
        }
        return notices;
    }


    /**
     * Notice构建查询对象
     * @param notice
     * @return
     */
    public QueryWrapper<Notice> createWrapper(Notice notice){
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        if(notice!=null){
            // ID
            if(!StringUtils.isEmpty(notice.getId())){
                    queryWrapper.eq("id",notice.getId());
            }
            // 接收消息用户的ID
            if(!StringUtils.isEmpty(notice.getReceiverId())){
                    queryWrapper.eq("receiverId",notice.getReceiverId());
            }
            // 进行操作用户的ID
            if(!StringUtils.isEmpty(notice.getOperatorId())){
                    queryWrapper.eq("operatorId",notice.getOperatorId());
            }
            // 操作类型（评论，点赞等）
            if(!StringUtils.isEmpty(notice.getAction())){
                    queryWrapper.eq("action",notice.getAction());
            }
            // 被操作的对象，例如文章，评论等
            if(!StringUtils.isEmpty(notice.getTargetType())){
                    queryWrapper.eq("targetType",notice.getTargetType());
            }
            // 被操作对象的id，例如文章的id，评论的id
            if(!StringUtils.isEmpty(notice.getTargetId())){
                    queryWrapper.eq("targetId",notice.getTargetId());
            }
            // 发表日期
            if(!StringUtils.isEmpty(notice.getCreatetime())){
                    queryWrapper.eq("createtime",notice.getCreatetime());
            }
            // 通知类型
            if(!StringUtils.isEmpty(notice.getType())){
                    queryWrapper.eq("type",notice.getType());
            }
            //
            if(!StringUtils.isEmpty(notice.getState())){
                    queryWrapper.eq("state",notice.getState());
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
        noticeMapper.deleteById(id);
    }

    /**
     * 修改Notice
     * @param notice
     */
    @Override
    public void update(Notice notice){
        noticeMapper.updateById(notice);
    }

    /**
     * 增加Notice
     * @param notice
     */
    @Override
    public void add(Notice notice){
        //设置主键值
        notice.setId(idWorker.nextId()+"");
        //设置消息的状态0 未读
        notice.setState("0");
        noticeMapper.insert(notice);
        //待推送消息入库，新消息提示查询此表
        NoticeFresh noticeFresh = new NoticeFresh();
        noticeFresh.setId(idWorker.nextId()+"");
        // 消息id
        noticeFresh.setNoticeId(notice.getId());
        //待通知用户的id
        noticeFresh.setUserId(notice.getReceiverId());
        noticeFreshMapper.insert(noticeFresh);
    }

    /**
     * 根据ID查询Notice
     * @param id
     * @return
     */
    @Override
    public Notice findById(String id){
        Notice notice = noticeMapper.selectById(id);
        getOtherNoticeInfo(notice);
        return notice;
    }

    /**
     * 查询notice中的其他信息
     * @param notice
     * @return
     */
    private void getOtherNoticeInfo(Notice notice) {
        //获取用户昵称
        Result<User> userResult = userFeign.findById(notice.getReceiverId());
        notice.setOperatorName(userResult.getData().getNickname());
        //获取文章标题
        if (notice.getTargetType().equalsIgnoreCase("article")){
            //被操作的对象是文章
            Result<Article> articleResult = articleFeign.findById(notice.getTargetId());
            notice.setTargetName(articleResult.getData().getTitle());
        }
    }

    /**
     * 查询Notice全部数据
     * @return
     */
    @Override
    public List<Notice> findAll() {
        List<Notice> notices = noticeMapper.selectList(null);
        for (Notice notice : notices) {
            getOtherNoticeInfo(notice);
        }
        return notices;
    }
}
