package cn.lx.tensquare.notice.controller;
import cn.lx.tensquare.notice.pojo.Notice;
import cn.lx.tensquare.notice.service.NoticeService;
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
@Api(value = "NoticeController")
@RestController
@RequestMapping("/notice")
@CrossOrigin
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /***
     * Notice分页条件搜索实现
     * @param notice
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Notice条件分页查询",notes = "分页条件查询Notice方法详情",tags = {"NoticeController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public PageResult<Notice> findPage(@RequestBody(required = false) @ApiParam(name = "Notice对象",value = "传入JSON数据",required = false) Notice notice, @PathVariable  int page, @PathVariable  int size){
        //调用NoticeService实现分页条件查询Notice
        Page<Notice> pageInfo = noticeService.findPage(notice, page, size);
        return new PageResult<Notice>(pageInfo.getTotal(),pageInfo.getRecords());
    }

    /***
     * Notice分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Notice分页查询",notes = "分页查询Notice方法详情",tags = {"NoticeController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public PageResult<Notice> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用NoticeService实现分页查询Notice
        Page<Notice> pageInfo = noticeService.findPage(page, size);
        return new PageResult<Notice>(pageInfo.getTotal(),pageInfo.getRecords());
    }

    /***
     * 多条件搜索Notice数据
     * @param notice
     * @return
     */
    @ApiOperation(value = "Notice条件查询",notes = "条件查询Notice方法详情",tags = {"NoticeController"})
    @PostMapping(value = "/search" )
    public Result<List<Notice>> findList(@RequestBody(required = false) @ApiParam(name = "Notice对象",value = "传入JSON数据",required = false) Notice notice){
        //调用NoticeService实现条件查询Notice
        List<Notice> list = noticeService.findList(notice);
        return new Result<List<Notice>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除Notice数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Notice根据ID删除",notes = "根据ID删除Notice方法详情",tags = {"NoticeController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用NoticeService实现根据主键删除
        noticeService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Notice数据
     * @param notice
     * @param id
     * @return
     */
    @ApiOperation(value = "Notice根据ID修改",notes = "根据ID修改Notice方法详情",tags = {"NoticeController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Notice对象",value = "传入JSON数据",required = false) Notice notice,@PathVariable String id){
        //设置主键值
        notice.setId(id);
        //调用NoticeService实现修改Notice
        noticeService.update(notice);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Notice数据
     * @param notice
     * @return
     */
    @ApiOperation(value = "Notice添加",notes = "添加Notice方法详情",tags = {"NoticeController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Notice对象",value = "传入JSON数据",required = true) Notice notice){
        //调用NoticeService实现添加Notice
        noticeService.add(notice);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Notice数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Notice根据ID查询",notes = "根据ID查询Notice方法详情",tags = {"NoticeController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public Result<Notice> findById(@PathVariable String id){
        //调用NoticeService实现根据主键查询Notice
        Notice notice = noticeService.findById(id);
        return new Result<Notice>(true,StatusCode.OK,"查询成功",notice);
    }

    /***
     * 查询Notice全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Notice",notes = "查询所Notice有方法详情",tags = {"NoticeController"})
    @GetMapping
    public Result<List<Notice>> findAll(){
        //调用NoticeService实现查询所有Notice
        List<Notice> list = noticeService.findAll();
        return new Result<List<Notice>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
