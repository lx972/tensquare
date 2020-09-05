package cn.lx.tensquare.article.service.impl;

import cn.lx.tensquare.article.dao.ChannelMapper;
import cn.lx.tensquare.article.pojo.Channel;
import cn.lx.tensquare.article.service.ChannelService;
import cn.lx.tensquare.utils.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Channel业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private IdWorker idWorker;


    /**
     * Channel条件+分页查询
     * @param channel 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Channel> findPage(Channel channel, int page, int size){
        //分页
        Page<Channel> page1 = new Page<>(page, size);
        //搜索条件构建
        QueryWrapper<Channel> queryWrapper = createWrapper(channel);
        //执行搜索
        Page<Channel> pageInfo = (Page<Channel>) channelMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * Channel分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Channel> findPage(int page, int size){
        //静态分页
        Page<Channel> page1 = new Page<>(page, size);
        //分页查询
        QueryWrapper<Channel> queryWrapper = new QueryWrapper<>();
        Page<Channel> pageInfo = (Page<Channel>) channelMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * Channel条件查询
     * @param channel
     * @return
     */
    @Override
    public List<Channel> findList(Channel channel){
        //构建查询条件
        QueryWrapper<Channel> queryWrapper = createWrapper(channel);
        //根据构建的条件查询数据
        return channelMapper.selectList(queryWrapper);
    }


    /**
     * Channel构建查询对象
     * @param channel
     * @return
     */
    public QueryWrapper<Channel> createWrapper(Channel channel){
        QueryWrapper<Channel> queryWrapper = new QueryWrapper<>();
        if(channel!=null){
            // ID
            if(!StringUtils.isEmpty(channel.getId())){
                    queryWrapper.eq("id",channel.getId());
            }
            // 频道名称
            if(!StringUtils.isEmpty(channel.getName())){
                    queryWrapper.eq("name","%"+channel.getName()+"%");
            }
            // 状态
            if(!StringUtils.isEmpty(channel.getState())){
                    queryWrapper.eq("state",channel.getState());
            }
        }
        return queryWrapper;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        channelMapper.deleteById(id);
    }

    /**
     * 修改Channel
     * @param channel
     */
    @Override
    public void update(Channel channel){
        channelMapper.updateById(channel);
    }

    /**
     * 增加Channel
     * @param channel
     */
    @Override
    public void add(Channel channel){
        //设置主键值
        channel.setId(idWorker.nextId()+"");
        channelMapper.insert(channel);
    }

    /**
     * 根据ID查询Channel
     * @param id
     * @return
     */
    @Override
    public Channel findById(String id){
        return  channelMapper.selectById(id);
    }

    /**
     * 查询Channel全部数据
     * @return
     */
    @Override
    public List<Channel> findAll() {
        return channelMapper.selectList(null);
    }
}
