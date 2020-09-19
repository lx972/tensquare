package cn.lx.tensquare.user.service;
import cn.lx.tensquare.user.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/****
 * @Author:lx
 * @Description:User业务层接口
 * @Date 2020/9/5 10:33
 *****/
public interface UserService {

    /***
     * User多条件分页查询
     * @param user
     * @param page
     * @param size
     * @return
     */
    Page<User> findPage(User user, int page, int size);

    /***
     * User分页查询
     * @param page
     * @param size
     * @return
     */
    Page<User> findPage(int page, int size);

    /***
     * User多条件搜索方法
     * @param user
     * @return
     */
    List<User> findList(User user);

    /***
     * 删除User
     * @param id
     */
    void delete(String id);

    /***
     * 修改User数据
     * @param user
     */
    void update(User user);

    /***
     * 新增User
     * @param user
     */
    void add(User user);

    /**
     * 根据ID查询User
     * @param id
     * @return
     */
     User findById(String id);

    /***
     * 查询所有User
     * @return
     */
    List<User> findAll();
}
