package cn.lx.tensquare.notice.service.impl;
import cn.lx.tensquare.notice.dao.NoticeFreshMapper;
import cn.lx.tensquare.notice.pojo.NoticeFresh;
import cn.lx.tensquare.notice.service.NoticeFreshService;
import cn.lx.tensquare.utils.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:NoticeFresh业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class NoticeFreshServiceImpl implements NoticeFreshService {

    @Autowired
    private NoticeFreshMapper noticeFreshMapper;

    @Autowired
    private IdWorker idWorker;


    /**
     * NoticeFresh条件+分页查询
     * @param noticeFresh 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<NoticeFresh> findPage(NoticeFresh noticeFresh, int page, int size){
        //分页
        Page<NoticeFresh> page1 = new Page<>(page, size);
        //搜索条件构建
        QueryWrapper<NoticeFresh> queryWrapper = createWrapper(noticeFresh);
        //执行搜索
        Page<NoticeFresh> pageInfo = (Page<NoticeFresh>) noticeFreshMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * NoticeFresh分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<NoticeFresh> findPage(int page, int size){
        //静态分页
        Page<NoticeFresh> page1 = new Page<>(page, size);
        //分页查询
        QueryWrapper<NoticeFresh> queryWrapper = new QueryWrapper<>();
        Page<NoticeFresh> pageInfo = (Page<NoticeFresh>) noticeFreshMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * NoticeFresh条件查询
     * @param noticeFresh
     * @return
     */
    @Override
    public List<NoticeFresh> findList(NoticeFresh noticeFresh){
        //构建查询条件
        QueryWrapper<NoticeFresh> queryWrapper = createWrapper(noticeFresh);
        //根据构建的条件查询数据
        return noticeFreshMapper.selectList(queryWrapper);
    }


    /**
     * NoticeFresh构建查询对象
     * @param noticeFresh
     * @return
     */
    public QueryWrapper<NoticeFresh> createWrapper(NoticeFresh noticeFresh){
        QueryWrapper<NoticeFresh> queryWrapper = new QueryWrapper<>();
        if(noticeFresh!=null){
            // 
            if(!StringUtils.isEmpty(noticeFresh.getId())){
                    queryWrapper.eq("id",noticeFresh.getId());
            }
            // 用户id
            if(!StringUtils.isEmpty(noticeFresh.getUserId())){
                    queryWrapper.eq("userId",noticeFresh.getUserId());
            }
            // 通知id
            if(!StringUtils.isEmpty(noticeFresh.getNoticeId())){
                    queryWrapper.eq("noticeId",noticeFresh.getNoticeId());
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
        noticeFreshMapper.deleteById(id);
    }

    /**
     * 修改NoticeFresh
     * @param noticeFresh
     */
    @Override
    public void update(NoticeFresh noticeFresh){
        noticeFreshMapper.updateById(noticeFresh);
    }

    /**
     * 增加NoticeFresh
     * @param noticeFresh
     */
    @Override
    public void add(NoticeFresh noticeFresh){
        //设置主键值
        noticeFresh.setId(idWorker.nextId()+"");
        noticeFreshMapper.insert(noticeFresh);
    }

    /**
     * 根据ID查询NoticeFresh
     * @param id
     * @return
     */
    @Override
    public NoticeFresh findById(String id){
        return  noticeFreshMapper.selectById(id);
    }

    /**
     * 查询NoticeFresh全部数据
     * @return
     */
    @Override
    public List<NoticeFresh> findAll() {
        return noticeFreshMapper.selectList(null);
    }
}
