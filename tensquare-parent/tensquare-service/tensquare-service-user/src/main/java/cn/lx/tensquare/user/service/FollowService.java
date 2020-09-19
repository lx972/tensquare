package cn.lx.tensquare.user.service;
import cn.lx.tensquare.user.pojo.Follow;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/****
 * @Author:lx
 * @Description:Follow业务层接口
 * @Date 2020/9/5 10:33
 *****/
public interface FollowService {

    /***
     * Follow多条件分页查询
     * @param follow
     * @param page
     * @param size
     * @return
     */
    Page<Follow> findPage(Follow follow, int page, int size);

    /***
     * Follow分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Follow> findPage(int page, int size);

    /***
     * Follow多条件搜索方法
     * @param follow
     * @return
     */
    List<Follow> findList(Follow follow);

    /***
     * 删除Follow
     * @param id
     */
    void delete(String id);

    /***
     * 修改Follow数据
     * @param follow
     */
    void update(Follow follow);

    /***
     * 新增Follow
     * @param follow
     */
    void add(Follow follow);

    /**
     * 根据ID查询Follow
     * @param id
     * @return
     */
     Follow findById(String id);

    /***
     * 查询所有Follow
     * @return
     */
    List<Follow> findAll();
}
