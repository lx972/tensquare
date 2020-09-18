package cn.lx.tensquare.comment.controller;

import cn.lx.tensquare.comment.pojo.Comment;
import cn.lx.tensquare.comment.service.CommentService;
import cn.lx.tensquare.entity.Result;
import cn.lx.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * cn.lx.tensquare.comment.controller
 *
 * @Author Administrator
 * @date 17:46
 */
@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    /**
     * 评论点赞
     * @param id
     * @return
     */
    @PutMapping(value = "/thumbup/{id}")
    public Result thumbup(@PathVariable("id") String id){
        String userId="12345";
        boolean flag = commentService.thumbup(id, userId);
        if (flag){
            return new Result<>(true, StatusCode.OK,"评论点赞成功");
        }
        return new Result<>(true, StatusCode.OK,"取消点赞成功");
    }

    /**
     * 根据articleid查询评论信息
     * @param articleid
     * @return
     */
    @GetMapping(value = "/articleid/{articleid}")
    public Result<List<Comment>> findByArticleid(@PathVariable("articleid") String articleid){
        List<Comment> comments = commentService.findByArticleid(articleid);
        return new Result<>(true, StatusCode.OK,"根据articleid查询评论信息成功",comments);
    }

    /**
     * 根据id查询评论信息
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result<Comment> findById(@PathVariable("id") String id){
        Comment comment = commentService.findById(id);
        return new Result<>(true, StatusCode.OK,"查询成功",comment);
    }



    /**
     * 查询所有评论信息
     * @return
     */
    @GetMapping
    public Result<List<Comment>> findAll(){
        List<Comment> comments = commentService.findAll();
        return new Result<>(true, StatusCode.OK,"查询成功",comments);
    }

    /**
     * 新增评论信息
     * @param comment
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Comment comment){
        commentService.add(comment);
        return new Result<>(true, StatusCode.OK,"新增成功");
    }


    /**
     * 修改评论信息
     * @param id
     * @param comment
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable("id") String id,@RequestBody Comment comment){
        comment.set_id(id);
        commentService.update(comment);
        return new Result<>(true, StatusCode.OK,"修改成功");
    }


    /**
     * 删除评论信息
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable("id") String id){
        commentService.delete(id);
        return new Result<>(true, StatusCode.OK,"删除成功");
    }
}
