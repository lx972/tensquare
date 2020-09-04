package cn.lx.tensquare.article.service.impl;

import cn.lx.tensquare.article.dao.ChannelMapper;
import cn.lx.tensquare.article.pojo.Channel;
import cn.lx.tensquare.article.service.ChannelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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


    /**
     * Channel条件+分页查询
     * @param channel 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Channel> findPage(Channel channel, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(channel);
        //执行搜索
        return new PageInfo<Channel>(channelMapper.selectByExample(example));
    }

    /**
     * Channel分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Channel> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Channel>(channelMapper.selectAll());
    }

    /**
     * Channel条件查询
     * @param channel
     * @return
     */
    @Override
    public List<Channel> findList(Channel channel){
        //构建查询条件
        Example example = createExample(channel);
        //根据构建的条件查询数据
        return channelMapper.selectByExample(example);
    }


    /**
     * Channel构建查询对象
     * @param channel
     * @return
     */
    public Example createExample(Channel channel){
        Example example=new Example(Channel.class);
        Example.Criteria criteria = example.createCriteria();
        if(channel!=null){
            // ID
            if(!StringUtils.isEmpty(channel.getId())){
                    criteria.andEqualTo("id",channel.getId());
            }
            // 频道名称
            if(!StringUtils.isEmpty(channel.getName())){
                    criteria.andLike("name","%"+channel.getName()+"%");
            }
            // 状态
            if(!StringUtils.isEmpty(channel.getState())){
                    criteria.andEqualTo("state",channel.getState());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        channelMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Channel
     * @param channel
     */
    @Override
    public void update(Channel channel){
        channelMapper.updateByPrimaryKey(channel);
    }

    /**
     * 增加Channel
     * @param channel
     */
    @Override
    public void add(Channel channel){
        channelMapper.insert(channel);
    }

    /**
     * 根据ID查询Channel
     * @param id
     * @return
     */
    @Override
    public Channel findById(String id){
        return  channelMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Channel全部数据
     * @return
     */
    @Override
    public List<Channel> findAll() {
        return channelMapper.selectAll();
    }
}
