package cn.lx.tensquare.user.controller;
import cn.lx.tensquare.user.pojo.Follow;
import cn.lx.tensquare.user.service.FollowService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.lx.tensquare.entity.PageResult;
import cn.lx.tensquare.entity.Result;
import cn.lx.tensquare.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/****
 * @Author:lx
 * @Description:
 * @Date 2020/9/5 10:33
 *****/
@Api(value = "FollowController")
@RestController
@RequestMapping("/follow")
@CrossOrigin
public class FollowController {

    @Autowired
    private FollowService followService;

    /***
     * Follow分页条件搜索实现
     * @param follow
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Follow条件分页查询",notes = "分页条件查询Follow方法详情",tags = {"FollowController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public PageResult<Follow> findPage(@RequestBody(required = false) @ApiParam(name = "Follow对象",value = "传入JSON数据",required = false) Follow follow, @PathVariable  int page, @PathVariable  int size){
        //调用FollowService实现分页条件查询Follow
        Page<Follow> pageInfo = followService.findPage(follow, page, size);
        return new PageResult<Follow>(pageInfo.getTotal(),pageInfo.getRecords());
    }

    /***
     * Follow分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Follow分页查询",notes = "分页查询Follow方法详情",tags = {"FollowController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public PageResult<Follow> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用FollowService实现分页查询Follow
        Page<Follow> pageInfo = followService.findPage(page, size);
        return new PageResult<Follow>(pageInfo.getTotal(),pageInfo.getRecords());
    }

    /***
     * 多条件搜索Follow数据
     * @param follow
     * @return
     */
    @ApiOperation(value = "Follow条件查询",notes = "条件查询Follow方法详情",tags = {"FollowController"})
    @PostMapping(value = "/search" )
    public Result<List<Follow>> findList(@RequestBody(required = false) @ApiParam(name = "Follow对象",value = "传入JSON数据",required = false) Follow follow){
        //调用FollowService实现条件查询Follow
        List<Follow> list = followService.findList(follow);
        return new Result<List<Follow>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除Follow数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Follow根据ID删除",notes = "根据ID删除Follow方法详情",tags = {"FollowController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用FollowService实现根据主键删除
        followService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Follow数据
     * @param follow
     * @param id
     * @return
     */
    @ApiOperation(value = "Follow根据ID修改",notes = "根据ID修改Follow方法详情",tags = {"FollowController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Follow对象",value = "传入JSON数据",required = false) Follow follow,@PathVariable String id){
        //设置主键值
        follow.setUserid(id);
        //调用FollowService实现修改Follow
        followService.update(follow);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Follow数据
     * @param follow
     * @return
     */
    @ApiOperation(value = "Follow添加",notes = "添加Follow方法详情",tags = {"FollowController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Follow对象",value = "传入JSON数据",required = true) Follow follow){
        //调用FollowService实现添加Follow
        followService.add(follow);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Follow数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Follow根据ID查询",notes = "根据ID查询Follow方法详情",tags = {"FollowController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public Result<Follow> findById(@PathVariable String id){
        //调用FollowService实现根据主键查询Follow
        Follow follow = followService.findById(id);
        return new Result<Follow>(true,StatusCode.OK,"查询成功",follow);
    }

    /***
     * 查询Follow全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Follow",notes = "查询所Follow有方法详情",tags = {"FollowController"})
    @GetMapping
    public Result<List<Follow>> findAll(){
        //调用FollowService实现查询所有Follow
        List<Follow> list = followService.findAll();
        return new Result<List<Follow>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
