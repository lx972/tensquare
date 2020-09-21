package cn.lx.tensquare.notice.service;
import cn.lx.tensquare.notice.pojo.Notice;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/****
 * @Author:lx
 * @Description:Notice业务层接口
 * @Date 2020/9/5 10:33
 *****/
public interface NoticeService {

    /***
     * Notice多条件分页查询
     * @param notice
     * @param page
     * @param size
     * @return
     */
    Page<Notice> findPage(Notice notice, int page, int size);

    /***
     * Notice分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Notice> findPage(int page, int size);

    /***
     * Notice多条件搜索方法
     * @param notice
     * @return
     */
    List<Notice> findList(Notice notice);

    /***
     * 删除Notice
     * @param id
     */
    void delete(String id);

    /***
     * 修改Notice数据
     * @param notice
     */
    void update(Notice notice);

    /***
     * 新增Notice
     * @param notice
     */
    void add(Notice notice);

    /**
     * 根据ID查询Notice
     * @param id
     * @return
     */
     Notice findById(String id);

    /***
     * 查询所有Notice
     * @return
     */
    List<Notice> findAll();
}
