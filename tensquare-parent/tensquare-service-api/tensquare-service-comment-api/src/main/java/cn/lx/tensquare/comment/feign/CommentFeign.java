package cn.lx.tensquare.comment.feign;

import cn.lx.tensquare.comment.pojo.Comment;
import cn.lx.tensquare.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * cn.lx.tensquare.comment.feign
 *
 * @Author Administrator
 * @date 17:07
 */
@FeignClient(name = "comment")
@RequestMapping("/comment")
public interface CommentFeign {

    /**
     * 根据id查询评论信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Comment> findById(@PathVariable String id);
}
