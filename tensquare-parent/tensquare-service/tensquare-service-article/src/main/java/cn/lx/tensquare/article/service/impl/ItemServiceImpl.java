package cn.lx.tensquare.article.service.impl;

import cn.lx.tensquare.article.dao.ItemMapper;
import cn.lx.tensquare.article.pojo.Item;
import cn.lx.tensquare.article.service.ItemService;
import cn.lx.tensquare.utils.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Item业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private IdWorker idWorker;


    /**
     * Item条件+分页查询
     * @param item 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Item> findPage(Item item, int page, int size){
        //分页
        Page<Item> page1 = new Page<>(page, size);
        //搜索条件构建
        QueryWrapper<Item> queryWrapper = createWrapper(item);
        //执行搜索
        Page<Item> pageInfo = (Page<Item>) itemMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * Item分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Item> findPage(int page, int size){
        //静态分页
        Page<Item> page1 = new Page<>(page, size);
        //分页查询
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        Page<Item> pageInfo = (Page<Item>) itemMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * Item条件查询
     * @param item
     * @return
     */
    @Override
    public List<Item> findList(Item item){
        //构建查询条件
        QueryWrapper<Item> queryWrapper = createWrapper(item);
        //根据构建的条件查询数据
        return itemMapper.selectList(queryWrapper);
    }


    /**
     * Item构建查询对象
     * @param item
     * @return
     */
    public QueryWrapper<Item> createWrapper(Item item){
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        if(item!=null){
            // ID
            if(!StringUtils.isEmpty(item.getId())){
                    queryWrapper.eq("id",item.getId());
            }
            // 专栏名称
            if(!StringUtils.isEmpty(item.getName())){
                    queryWrapper.eq("name","%"+item.getName()+"%");
            }
            // 专栏简介
            if(!StringUtils.isEmpty(item.getSummary())){
                    queryWrapper.eq("summary",item.getSummary());
            }
            // 用户ID
            if(!StringUtils.isEmpty(item.getUserid())){
                    queryWrapper.eq("userid",item.getUserid());
            }
            // 申请日期
            if(!StringUtils.isEmpty(item.getCreatetime())){
                    queryWrapper.eq("createtime",item.getCreatetime());
            }
            // 审核日期
            if(!StringUtils.isEmpty(item.getChecktime())){
                    queryWrapper.eq("checktime",item.getChecktime());
            }
            // 状态
            if(!StringUtils.isEmpty(item.getState())){
                    queryWrapper.eq("state",item.getState());
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
        itemMapper.deleteById(id);
    }

    /**
     * 修改Item
     * @param item
     */
    @Override
    public void update(Item item){
        itemMapper.updateById(item);
    }

    /**
     * 增加Item
     * @param item
     */
    @Override
    public void add(Item item){
        //设置主键值
        item.setId(idWorker.nextId()+"");
        itemMapper.insert(item);
    }

    /**
     * 根据ID查询Item
     * @param id
     * @return
     */
    @Override
    public Item findById(String id){
        return  itemMapper.selectById(id);
    }

    /**
     * 查询Item全部数据
     * @return
     */
    @Override
    public List<Item> findAll() {
        return itemMapper.selectList(null);
    }
}
