package cn.lx.tensquare.article.service;

import cn.lx.tensquare.article.pojo.Item;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/****
 * @Author:lx
 * @Description:Item业务层接口
 * @Date 2020/9/5 10:33
 *****/
public interface ItemService {

    /***
     * Item多条件分页查询
     * @param item
     * @param page
     * @param size
     * @return
     */
    Page<Item> findPage(Item item, int page, int size);

    /***
     * Item分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Item> findPage(int page, int size);

    /***
     * Item多条件搜索方法
     * @param item
     * @return
     */
    List<Item> findList(Item item);

    /***
     * 删除Item
     * @param id
     */
    void delete(String id);

    /***
     * 修改Item数据
     * @param item
     */
    void update(Item item);

    /***
     * 新增Item
     * @param item
     */
    void add(Item item);

    /**
     * 根据ID查询Item
     * @param id
     * @return
     */
     Item findById(String id);

    /***
     * 查询所有Item
     * @return
     */
    List<Item> findAll();
}
