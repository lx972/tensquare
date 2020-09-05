package cn.lx.tensquare.article.controller;

import cn.lx.tensquare.article.pojo.Channel;
import cn.lx.tensquare.article.service.ChannelService;
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
@Api(value = "ChannelController")
@RestController
@RequestMapping("/channel")
@CrossOrigin
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    /***
     * Channel分页条件搜索实现
     * @param channel
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Channel条件分页查询",notes = "分页条件查询Channel方法详情",tags = {"ChannelController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public PageResult<Channel> findPage(@RequestBody(required = false) @ApiParam(name = "Channel对象",value = "传入JSON数据",required = false) Channel channel, @PathVariable  int page, @PathVariable  int size){
        //调用ChannelService实现分页条件查询Channel
        Page<Channel> pageInfo = channelService.findPage(channel, page, size);
        return new PageResult<Channel>(pageInfo.getTotal(),pageInfo.getRecords());
    }

    /***
     * Channel分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Channel分页查询",notes = "分页查询Channel方法详情",tags = {"ChannelController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public PageResult<Channel> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用ChannelService实现分页查询Channel
        Page<Channel> pageInfo = channelService.findPage(page, size);
        return new PageResult<Channel>(pageInfo.getTotal(),pageInfo.getRecords());
    }

    /***
     * 多条件搜索Channel数据
     * @param channel
     * @return
     */
    @ApiOperation(value = "Channel条件查询",notes = "条件查询Channel方法详情",tags = {"ChannelController"})
    @PostMapping(value = "/search" )
    public Result<List<Channel>> findList(@RequestBody(required = false) @ApiParam(name = "Channel对象",value = "传入JSON数据",required = false) Channel channel){
        //调用ChannelService实现条件查询Channel
        List<Channel> list = channelService.findList(channel);
        return new Result<List<Channel>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除Channel数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Channel根据ID删除",notes = "根据ID删除Channel方法详情",tags = {"ChannelController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用ChannelService实现根据主键删除
        channelService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Channel数据
     * @param channel
     * @param id
     * @return
     */
    @ApiOperation(value = "Channel根据ID修改",notes = "根据ID修改Channel方法详情",tags = {"ChannelController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Channel对象",value = "传入JSON数据",required = false) Channel channel, @PathVariable String id){
        //设置主键值
        channel.setId(id);
        //调用ChannelService实现修改Channel
        channelService.update(channel);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Channel数据
     * @param channel
     * @return
     */
    @ApiOperation(value = "Channel添加",notes = "添加Channel方法详情",tags = {"ChannelController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Channel对象",value = "传入JSON数据",required = true) Channel channel){
        //调用ChannelService实现添加Channel
        channelService.add(channel);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Channel数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Channel根据ID查询",notes = "根据ID查询Channel方法详情",tags = {"ChannelController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public Result<Channel> findById(@PathVariable String id){
        //调用ChannelService实现根据主键查询Channel
        Channel channel = channelService.findById(id);
        return new Result<Channel>(true,StatusCode.OK,"查询成功",channel);
    }

    /***
     * 查询Channel全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Channel",notes = "查询所Channel有方法详情",tags = {"ChannelController"})
    @GetMapping
    public Result<List<Channel>> findAll(){
        //调用ChannelService实现查询所有Channel
        List<Channel> list = channelService.findAll();
        return new Result<List<Channel>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
