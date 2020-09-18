package cn.lx.tensquare.comment.service.impl;

import cn.lx.tensquare.comment.pojo.Comment;
import cn.lx.tensquare.comment.repository.CommentRepository;
import cn.lx.tensquare.comment.service.CommentService;
import cn.lx.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * cn.lx.tensquare.comment.service.impl
 *
 * @Author Administrator
 * @date 17:44
 */
@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private IdWorker idWorker;


    @Autowired
    private MongoTemplate mongoTemplate;


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 根据id查询评论信息
     * @param id
     * @return
     */
    @Override
    public Comment findById(String id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        Comment comment = optionalComment.get();
        return comment;
    }


    /**
     * 查询所有评论信息
     * @return
     */
    @Override
    public List<Comment> findAll() {
        List<Comment> commentList = commentRepository.findAll();
        return commentList;
    }

    /**
     * 新增评论信息
     * @param comment
     */
    @Override
    public void add(Comment comment) {
        comment.set_id(idWorker.nextId()+"");
        commentRepository.insert(comment);
    }

    /**
     * 修改评论信息
     * @param comment
     */
    @Override
    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * 删除评论信息
     * @param id
     */
    @Override
    public void delete(String id) {
        commentRepository.deleteById(id);
    }

    /**
     * 根据文章id查询评论信息
     * @param articleid
     * @return
     */
    @Override
    public List<Comment> findByArticleid(String articleid) {
        return commentRepository.findCommentByArticleid(articleid);
    }

    /**
     * 评论点赞
     *  @param id    文章id
     * @param userId    用户id
     * @return  取消点赞返回false，点赞成功返回true
     */
    @Override
    public boolean thumbup(String id, String userId) {
        //判断是否重复点赞
        if (redisTemplate.boundHashOps("thumbup").hasKey(id+"_"+userId)){
            //说明该用户已经点赞
            redisTemplate.boundHashOps("thumbup").delete(id+"_"+userId);
            //取消点赞返回false
            return false;
        }
        //说明该用户未点赞
        Long thumbup = redisTemplate.boundHashOps("thumbup").increment(id+"_"+userId, 1);
        //查询条件
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        //更新的数据
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"comment");
        //点赞成功返回true
        return true;
    }
}
