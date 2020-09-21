package cn.lx.tensquare.notice.controller;
import cn.lx.tensquare.notice.pojo.NoticeFresh;
import cn.lx.tensquare.notice.service.NoticeFreshService;
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
@Api(value = "NoticeFreshController")
@RestController
@RequestMapping("/noticeFresh")
@CrossOrigin
public class NoticeFreshController {

    @Autowired
    private NoticeFreshService noticeFreshService;

    /***
     * NoticeFresh分页条件搜索实现
     * @param noticeFresh
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "NoticeFresh条件分页查询",notes = "分页条件查询NoticeFresh方法详情",tags = {"NoticeFreshController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public PageResult<NoticeFresh> findPage(@RequestBody(required = false) @ApiParam(name = "NoticeFresh对象",value = "传入JSON数据",required = false) NoticeFresh noticeFresh, @PathVariable  int page, @PathVariable  int size){
        //调用NoticeFreshService实现分页条件查询NoticeFresh
        Page<NoticeFresh> pageInfo = noticeFreshService.findPage(noticeFresh, page, size);
        return new PageResult<NoticeFresh>(pageInfo.getTotal(),pageInfo.getRecords());
    }

    /***
     * NoticeFresh分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "NoticeFresh分页查询",notes = "分页查询NoticeFresh方法详情",tags = {"NoticeFreshController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public PageResult<NoticeFresh> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用NoticeFreshService实现分页查询NoticeFresh
        Page<NoticeFresh> pageInfo = noticeFreshService.findPage(page, size);
        return new PageResult<NoticeFresh>(pageInfo.getTotal(),pageInfo.getRecords());
    }

    /***
     * 多条件搜索NoticeFresh数据
     * @param noticeFresh
     * @return
     */
    @ApiOperation(value = "NoticeFresh条件查询",notes = "条件查询NoticeFresh方法详情",tags = {"NoticeFreshController"})
    @PostMapping(value = "/search" )
    public Result<List<NoticeFresh>> findList(@RequestBody(required = false) @ApiParam(name = "NoticeFresh对象",value = "传入JSON数据",required = false) NoticeFresh noticeFresh){
        //调用NoticeFreshService实现条件查询NoticeFresh
        List<NoticeFresh> list = noticeFreshService.findList(noticeFresh);
        return new Result<List<NoticeFresh>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除NoticeFresh数据
     * @param id
     * @return
     */
    @ApiOperation(value = "NoticeFresh根据ID删除",notes = "根据ID删除NoticeFresh方法详情",tags = {"NoticeFreshController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用NoticeFreshService实现根据主键删除
        noticeFreshService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改NoticeFresh数据
     * @param noticeFresh
     * @param id
     * @return
     */
    @ApiOperation(value = "NoticeFresh根据ID修改",notes = "根据ID修改NoticeFresh方法详情",tags = {"NoticeFreshController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "NoticeFresh对象",value = "传入JSON数据",required = false) NoticeFresh noticeFresh,@PathVariable String id){
        //设置主键值
        noticeFresh.setId(id);
        //调用NoticeFreshService实现修改NoticeFresh
        noticeFreshService.update(noticeFresh);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增NoticeFresh数据
     * @param noticeFresh
     * @return
     */
    @ApiOperation(value = "NoticeFresh添加",notes = "添加NoticeFresh方法详情",tags = {"NoticeFreshController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "NoticeFresh对象",value = "传入JSON数据",required = true) NoticeFresh noticeFresh){
        //调用NoticeFreshService实现添加NoticeFresh
        noticeFreshService.add(noticeFresh);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询NoticeFresh数据
     * @param id
     * @return
     */
    @ApiOperation(value = "NoticeFresh根据ID查询",notes = "根据ID查询NoticeFresh方法详情",tags = {"NoticeFreshController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public Result<NoticeFresh> findById(@PathVariable String id){
        //调用NoticeFreshService实现根据主键查询NoticeFresh
        NoticeFresh noticeFresh = noticeFreshService.findById(id);
        return new Result<NoticeFresh>(true,StatusCode.OK,"查询成功",noticeFresh);
    }

    /***
     * 查询NoticeFresh全部数据
     * @return
     */
    @ApiOperation(value = "查询所有NoticeFresh",notes = "查询所NoticeFresh有方法详情",tags = {"NoticeFreshController"})
    @GetMapping
    public Result<List<NoticeFresh>> findAll(){
        //调用NoticeFreshService实现查询所有NoticeFresh
        List<NoticeFresh> list = noticeFreshService.findAll();
        return new Result<List<NoticeFresh>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
