package cn.lx.tensquare.article.service.impl;

import cn.lx.tensquare.article.dao.ItemMapper;
import cn.lx.tensquare.article.pojo.Item;
import cn.lx.tensquare.article.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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


    /**
     * Item条件+分页查询
     * @param item 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Item> findPage(Item item, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(item);
        //执行搜索
        return new PageInfo<Item>(itemMapper.selectByExample(example));
    }

    /**
     * Item分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Item> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Item>(itemMapper.selectAll());
    }

    /**
     * Item条件查询
     * @param item
     * @return
     */
    @Override
    public List<Item> findList(Item item){
        //构建查询条件
        Example example = createExample(item);
        //根据构建的条件查询数据
        return itemMapper.selectByExample(example);
    }


    /**
     * Item构建查询对象
     * @param item
     * @return
     */
    public Example createExample(Item item){
        Example example=new Example(Item.class);
        Example.Criteria criteria = example.createCriteria();
        if(item!=null){
            // ID
            if(!StringUtils.isEmpty(item.getId())){
                    criteria.andEqualTo("id",item.getId());
            }
            // 专栏名称
            if(!StringUtils.isEmpty(item.getName())){
                    criteria.andLike("name","%"+item.getName()+"%");
            }
            // 专栏简介
            if(!StringUtils.isEmpty(item.getSummary())){
                    criteria.andEqualTo("summary",item.getSummary());
            }
            // 用户ID
            if(!StringUtils.isEmpty(item.getUserid())){
                    criteria.andEqualTo("userid",item.getUserid());
            }
            // 申请日期
            if(!StringUtils.isEmpty(item.getCreatetime())){
                    criteria.andEqualTo("createtime",item.getCreatetime());
            }
            // 审核日期
            if(!StringUtils.isEmpty(item.getChecktime())){
                    criteria.andEqualTo("checktime",item.getChecktime());
            }
            // 状态
            if(!StringUtils.isEmpty(item.getState())){
                    criteria.andEqualTo("state",item.getState());
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
        itemMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Item
     * @param item
     */
    @Override
    public void update(Item item){
        itemMapper.updateByPrimaryKey(item);
    }

    /**
     * 增加Item
     * @param item
     */
    @Override
    public void add(Item item){
        itemMapper.insert(item);
    }

    /**
     * 根据ID查询Item
     * @param id
     * @return
     */
    @Override
    public Item findById(String id){
        return  itemMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Item全部数据
     * @return
     */
    @Override
    public List<Item> findAll() {
        return itemMapper.selectAll();
    }
}
