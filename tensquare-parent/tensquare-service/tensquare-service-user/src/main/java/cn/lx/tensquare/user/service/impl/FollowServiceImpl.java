package cn.lx.tensquare.user.service.impl;
import cn.lx.tensquare.user.dao.FollowMapper;
import cn.lx.tensquare.user.pojo.Follow;
import cn.lx.tensquare.user.service.FollowService;
import cn.lx.tensquare.utils.IdWorker;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:Follow业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private IdWorker idWorker;


    /**
     * Follow条件+分页查询
     * @param follow 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public Page<Follow> findPage(Follow follow, int page, int size){
        //分页
        Page<Follow> page1 = new Page<>(page, size);
        //搜索条件构建
        QueryWrapper<Follow> queryWrapper = createWrapper(follow);
        //执行搜索
        Page<Follow> pageInfo = (Page<Follow>) followMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * Follow分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Follow> findPage(int page, int size){
        //静态分页
        Page<Follow> page1 = new Page<>(page, size);
        //分页查询
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        Page<Follow> pageInfo = (Page<Follow>) followMapper.selectPage(page1, queryWrapper);
        return pageInfo;
    }

    /**
     * Follow条件查询
     * @param follow
     * @return
     */
    @Override
    public List<Follow> findList(Follow follow){
        //构建查询条件
        QueryWrapper<Follow> queryWrapper = createWrapper(follow);
        //根据构建的条件查询数据
        return followMapper.selectList(queryWrapper);
    }


    /**
     * Follow构建查询对象
     * @param follow
     * @return
     */
    public QueryWrapper<Follow> createWrapper(Follow follow){
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        if(follow!=null){
            // 用户ID
            if(!StringUtils.isEmpty(follow.getUserid())){
                    queryWrapper.eq("userid",follow.getUserid());
            }
            // 被关注用户ID
            if(!StringUtils.isEmpty(follow.getTargetuser())){
                    queryWrapper.eq("targetuser",follow.getTargetuser());
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
        followMapper.deleteById(id);
    }

    /**
     * 修改Follow
     * @param follow
     */
    @Override
    public void update(Follow follow){
        followMapper.updateById(follow);
    }

    /**
     * 增加Follow
     * @param follow
     */
    @Override
    public void add(Follow follow){
        //设置主键值
        follow.setUserid(idWorker.nextId()+"");
        followMapper.insert(follow);
    }

    /**
     * 根据ID查询Follow
     * @param id
     * @return
     */
    @Override
    public Follow findById(String id){
        return  followMapper.selectById(id);
    }

    /**
     * 查询Follow全部数据
     * @return
     */
    @Override
    public List<Follow> findAll() {
        return followMapper.selectList(null);
    }
}
