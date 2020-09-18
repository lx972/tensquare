package cn.lx.tensquare.comment.service;

import cn.lx.tensquare.comment.pojo.Comment;

import java.util.List;

/**
 * cn.lx.tensquare.comment.service
 *
 * @Author Administrator
 * @date 17:43
 */
public interface CommentService {

    /**
     * 根据id查询评论信息
     * @param id
     * @return
     */
    Comment findById(String id);

    /**
     * 查询所有评论信息
     * @return
     */
    List<Comment> findAll();

    /**
     * 新增评论信息
     * @param comment
     */
    void add(Comment comment);

    /**
     * 修改评论信息
     * @param comment
     */
    void update(Comment comment);

    /**
     * 删除评论信息
     * @param id
     */
    void delete(String id);


    /**
     * 根据文章id查询评论信息
     * @param articleid
     * @return
     */
    List<Comment> findByArticleid(String articleid);

    /**
     * 评论点赞
     * @param id
     * @param userId
     * @return
     */
    boolean thumbup(String id, String userId);
}
