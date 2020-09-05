package cn.lx.tensquare.article.controller;

import cn.lx.tensquare.article.pojo.Article;
import cn.lx.tensquare.article.service.ArticleService;
import cn.lx.tensquare.entity.PageResult;
import cn.lx.tensquare.entity.Result;
import cn.lx.tensquare.entity.StatusCode;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:lx
 * @Description:
 * @Date 2020/9/5 10:33
 *****/
@Api(value = "ArticleController")
@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /***
     * Article分页条件搜索实现
     * @param article
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Article条件分页查询",notes = "分页条件查询Article方法详情",tags = {"ArticleController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public PageResult<Article> findPage(@RequestBody(required = false) @ApiParam(name = "Article对象",value = "传入JSON数据",required = false) Article article, @PathVariable  int page, @PathVariable  int size){
        //调用ArticleService实现分页条件查询Article
        Page<Article> pageInfo = articleService.findPage(article, page, size);
        return new PageResult<Article>(pageInfo.getTotal(),pageInfo.getRecords());
    }

    /***
     * Article分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Article分页查询",notes = "分页查询Article方法详情",tags = {"ArticleController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public PageResult<Article> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用ArticleService实现分页查询Article
        Page<Article> pageInfo = articleService.findPage(page, size);
        return new PageResult<Article>(pageInfo.getTotal(),pageInfo.getRecords());
    }

    /***
     * 多条件搜索Article数据
     * @param article
     * @return
     */
    @ApiOperation(value = "Article条件查询",notes = "条件查询Article方法详情",tags = {"ArticleController"})
    @PostMapping(value = "/search" )
    public Result<List<Article>> findList(@RequestBody(required = false) @ApiParam(name = "Article对象",value = "传入JSON数据",required = false) Article article){
        //调用ArticleService实现条件查询Article
        List<Article> list = articleService.findList(article);
        return new Result<List<Article>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除Article数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Article根据ID删除",notes = "根据ID删除Article方法详情",tags = {"ArticleController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用ArticleService实现根据主键删除
        articleService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Article数据
     * @param article
     * @param id
     * @return
     */
    @ApiOperation(value = "Article根据ID修改",notes = "根据ID修改Article方法详情",tags = {"ArticleController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Article对象",value = "传入JSON数据",required = false) Article article, @PathVariable String id){
        //设置主键值
        article.setId(id);
        //调用ArticleService实现修改Article
        articleService.update(article);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Article数据
     * @param article
     * @return
     */
    @ApiOperation(value = "Article添加",notes = "添加Article方法详情",tags = {"ArticleController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Article对象",value = "传入JSON数据",required = true) Article article){
        //调用ArticleService实现添加Article
        articleService.add(article);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Article数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Article根据ID查询",notes = "根据ID查询Article方法详情",tags = {"ArticleController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public Result<Article> findById(@PathVariable String id){
        //调用ArticleService实现根据主键查询Article
        Article article = articleService.findById(id);
        return new Result<Article>(true,StatusCode.OK,"查询成功",article);
    }

    /***
     * 查询Article全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Article",notes = "查询所Article有方法详情",tags = {"ArticleController"})
    @GetMapping
    public Result<List<Article>> findAll(){
        //调用ArticleService实现查询所有Article
        List<Article> list = articleService.findAll();
        return new Result<List<Article>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
