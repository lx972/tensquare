package cn.lx.tensquare.article.service;

import cn.lx.tensquare.article.pojo.Channel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/****
 * @Author:lx
 * @Description:Channel业务层接口
 * @Date 2020/9/5 10:33
 *****/
public interface ChannelService {

    /***
     * Channel多条件分页查询
     * @param channel
     * @param page
     * @param size
     * @return
     */
    Page<Channel> findPage(Channel channel, int page, int size);

    /***
     * Channel分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Channel> findPage(int page, int size);

    /***
     * Channel多条件搜索方法
     * @param channel
     * @return
     */
    List<Channel> findList(Channel channel);

    /***
     * 删除Channel
     * @param id
     */
    void delete(String id);

    /***
     * 修改Channel数据
     * @param channel
     */
    void update(Channel channel);

    /***
     * 新增Channel
     * @param channel
     */
    void add(Channel channel);

    /**
     * 根据ID查询Channel
     * @param id
     * @return
     */
     Channel findById(String id);

    /***
     * 查询所有Channel
     * @return
     */
    List<Channel> findAll();
}
