package cn.lx.tensquare.comment.repository;

import cn.lx.tensquare.comment.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * cn.lx.tensquare.comment.repository
 *
 * @Author Administrator
 * @date 17:40
 */
public interface CommentRepository extends MongoRepository<Comment,String> {


    /**
     * 根据文章id查询评论信息
     * @param articleid
     * @return
     */
    List<Comment> findCommentByArticleid(String articleid);
}
