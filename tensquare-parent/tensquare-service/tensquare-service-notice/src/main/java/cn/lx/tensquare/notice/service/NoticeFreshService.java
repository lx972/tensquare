package cn.lx.tensquare.notice.service;
import cn.lx.tensquare.notice.pojo.NoticeFresh;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/****
 * @Author:lx
 * @Description:NoticeFresh业务层接口
 * @Date 2020/9/5 10:33
 *****/
public interface NoticeFreshService {

    /***
     * NoticeFresh多条件分页查询
     * @param noticeFresh
     * @param page
     * @param size
     * @return
     */
    Page<NoticeFresh> findPage(NoticeFresh noticeFresh, int page, int size);

    /***
     * NoticeFresh分页查询
     * @param page
     * @param size
     * @return
     */
    Page<NoticeFresh> findPage(int page, int size);

    /***
     * NoticeFresh多条件搜索方法
     * @param noticeFresh
     * @return
     */
    List<NoticeFresh> findList(NoticeFresh noticeFresh);

    /***
     * 删除NoticeFresh
     * @param id
     */
    void delete(String id);

    /***
     * 修改NoticeFresh数据
     * @param noticeFresh
     */
    void update(NoticeFresh noticeFresh);

    /***
     * 新增NoticeFresh
     * @param noticeFresh
     */
    void add(NoticeFresh noticeFresh);

    /**
     * 根据ID查询NoticeFresh
     * @param id
     * @return
     */
     NoticeFresh findById(String id);

    /***
     * 查询所有NoticeFresh
     * @return
     */
    List<NoticeFresh> findAll();
}
